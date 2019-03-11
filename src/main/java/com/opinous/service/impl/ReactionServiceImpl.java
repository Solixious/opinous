package com.opinous.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;
import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Post;
import com.opinous.model.Reaction;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.ReactionRepository;
import com.opinous.service.AnonMapService;
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
	private AnonMapService anonMapService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AnonymousUserService anonymousUserService;
		
	@Override
	public void addReaction(final ReactionType reactionType, final String postId) {
		if(reactionType == null || postId == null) {
			log.error("ReactionType and post id cannot be null. reactionType: {}, postId: [}",
				reactionType, postId);
			return;
		}

		final Post post = postService.getPost(Long.parseLong(postId));
		if(exists(post, reactionType)) {
			return;
		}

		Reaction reaction = new Reaction();
		Room room = post.getAnonMap().getRoom();
		User user = userService.findByUsername(securityService.findLoggedInUsername());

		AnonMap anonMap = anonMapService.getAnonMapByRoomAndUser(room, user);
		if (anonMap == null) {
			AnonymousUser anonymousUser = anonymousUserService.generateAnonymousUser(room);
			anonMap = anonMapService.saveAnonMap(anonymousUser, user, room);
		}
		reaction.setAnonMap(anonMap);
		reaction.setPost(post);
		reaction.setReactionType(reactionType.name());
		reactionRepository.save(reaction);
	}

	@Override
	public void removeReaction(final ReactionType reactionType, final String postId) {
		if(reactionType == null || postId == null) {
			log.error("ReactionType and post id cannot be null. reactionType: {}, postId: [}",
				reactionType, postId);
			return;
		}

		Post post = postService.getPost(Long.parseLong(postId));
		if(exists(post, reactionType)) {
			reactionRepository.delete(getReaction(post, reactionType));;
		}
	}

	@Override
	public boolean exists(final Post post, final ReactionType reactionType) {
		if(reactionType == null || post == null) {
			log.error("Post and reactionType cannot be null. post: {}, reactionType: [}",
				post, reactionType);
			return false;
		}

		return reactionRepository.countByPostAndReactionTypeAndAnonMap_User_Username(post,
			reactionType.name(), securityService.findLoggedInUsername()) > 0;
	}

	@Override
	public Reaction getReaction(final Post post, final ReactionType reactionType) {
		if(reactionType == null || post == null) {
			log.error("Post and reactionType cannot be null. post: {}, reactionType: [}",
				post, reactionType);
			return null;
		}

		return reactionRepository.findByPostAndReactionTypeAndAnonMap_User_Username(post,
			reactionType.name(), securityService.findLoggedInUsername());
	}

	@Override
	public long getReactionCount(final Post post, final ReactionType reactionType) {
		if(reactionType == null || post == null) {
			log.error("Post and reactionType cannot be null. post: {}, reactionType: [}",
				post, reactionType);
			return 0L;
		}

		return reactionRepository.countByPostAndReactionType(post, reactionType.name());
	}

	@Override
	public Map<String, Long> getReactionCountMap(final Post post) {
		if(post == null) {
			log.error("Post cannot be null.");
			return null;
		}

		Map<String, Long> reactionMap = new HashMap<>();
		for(ReactionType reactionType : ReactionType.values()) {
			long count = getReactionCount(post, reactionType);
			reactionMap.put(reactionType.name(), count);
		}
		return reactionMap;
	}

	@Override
	public Map<String, Long> getReactionList(final Post post) {
		if(post == null) {
			log.error("Post cannot be null.");
			return null;
		}

		Map<String, Long> reactions = new HashMap<>();
		for(ReactionType reactionType : ReactionType.values()) {
			if(exists(post, reactionType))
				reactions.put(reactionType.name(), 1L);
		}
		return reactions;
	}
}
