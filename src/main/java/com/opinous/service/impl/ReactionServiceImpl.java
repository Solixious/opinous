package com.opinous.service.impl;

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
	public void addReaction(ReactionType reactionType, String postId) {
		Reaction reaction = new Reaction();
		Post post = postService.getPost(Long.parseLong(postId));
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
	public boolean exists(Post post, ReactionType reactionType) {
		return reactionRepository.countByPostAndReactionTypeAndAnonMap_User_Username(post,
			reactionType.name(), securityService.findLoggedInUsername()) > 0;
	}

	@Override
	public long getReactionCount(Post post, ReactionType reactionType) {
		return reactionRepository.countByPostAndReactionType(post, reactionType.name());
	}
}
