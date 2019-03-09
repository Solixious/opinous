package com.opinous.service;

import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;

@Service
public interface ReactionService {
	public void addReaction(ReactionType reactionType, String postId);
}
