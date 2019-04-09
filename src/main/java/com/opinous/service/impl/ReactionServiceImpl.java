package com.opinous.service.impl;

import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;
import com.opinous.model.Alias;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Post;
import com.opinous.model.Reaction;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.ReactionRepository;
import com.opinous.service.AliasService;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.PostService;
import com.opinous.service.ReactionService;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ReactionServiceImpl implements ReactionService {

	@Autowired
	private ReactionRepository reactionRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private AliasService aliasService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnonymousUserService anonymousUserService;
		
	@Override
	public void addReaction(final ReactionType reactionType, final String postId) {
		PreCondition.checkNotNull(postId, "postId");
		PreCondition.checkNotNull(reactionType, "reactionType");
		final Post post = postService.getPost(Long.parseLong(postId));
		if(exists(post, reactionType)) {
			return;
		}
		final Reaction reaction = new Reaction();
		final Room room = post.getAlias().getRoom();
		final User user = userService.getLoggedInUser();
		Alias alias = aliasService.getAliasByRoomAndUser(room, user);
		if (alias == null) {
			AnonymousUser anonymousUser = anonymousUserService.generateAnonymousUser(room);
			alias = aliasService.saveAlias(anonymousUser, user, room);
		}
		reaction.setAlias(alias);
		reaction.setPost(post);
		reaction.setReactionType(reactionType.name());
		reactionRepository.save(reaction);
	}

	@Override
	public void removeReaction(final ReactionType reactionType, final String postId) {
		PreCondition.checkNotNull(postId, "postId");
		PreCondition.checkNotNull(reactionType, "reactionType");
		final Post post = postService.getPost(Long.parseLong(postId));
		if(exists(post, reactionType)) {
			reactionRepository.delete(getReaction(post, reactionType));;
		}
	}

	@Override
	public boolean exists(final Post post, final ReactionType reactionType) {
		PreCondition.checkNotNull(post, "post");
		PreCondition.checkNotNull(reactionType, "reactionType");
		return reactionRepository.countByPostAndReactionTypeAndAlias_User_Username(post,
			reactionType.name(), securityService.findLoggedInUsername()) > 0;
	}

	@Override
	public Reaction getReaction(final Post post, final ReactionType reactionType) {
		PreCondition.checkNotNull(post, "post");
		PreCondition.checkNotNull(reactionType, "reactionType");
		return reactionRepository.findByPostAndReactionTypeAndAlias_User_Username(post,
			reactionType.name(), securityService.findLoggedInUsername());
	}

	@Override
	public long getReactionCount(final Post post, final ReactionType reactionType) {
		PreCondition.checkNotNull(post, "post");
		PreCondition.checkNotNull(reactionType, "reactionType");
		return reactionRepository.countByPostAndReactionType(post, reactionType.name());
	}

	@Override
	public Map<String, Long> getReactionCountMap(final Post post) {
		PreCondition.checkNotNull(post, "post");
		final Map<String, Long> reactionMap = new HashMap<>();
		for(ReactionType reactionType : ReactionType.values()) {
			final long count = getReactionCount(post, reactionType);
			reactionMap.put(reactionType.name(), count);
		}
		return reactionMap;
	}

	@Override
	public Map<String, Long> getReactionList(final Post post) {
		PreCondition.checkNotNull(post, "post");
		final Map<String, Long> reactions = new HashMap<>();
		for(ReactionType reactionType : ReactionType.values()) {
			if(exists(post, reactionType))
				reactions.put(reactionType.name(), 1L);
		}
		return reactions;
	}
}
