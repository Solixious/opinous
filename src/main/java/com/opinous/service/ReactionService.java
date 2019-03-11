package com.opinous.service;

import com.opinous.model.Post;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;

@Service
public interface ReactionService {
	public void addReaction(ReactionType reactionType, String postId);
	public boolean exists(Post post, ReactionType reactionType);
	public long getReactionCount(Post post, ReactionType reactionType);
}
