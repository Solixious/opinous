package com.opinous.service;

import com.opinous.model.Post;
import com.opinous.model.Reaction;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;

import java.util.Map;

@Service
public interface ReactionService {

	void addReaction(ReactionType reactionType, String postId);

	void removeReaction(ReactionType reactionType, String postId);

	boolean exists(Post post, ReactionType reactionType);

	Reaction getReaction(Post post, ReactionType reactionType);

	long getReactionCount(Post post, ReactionType reactionType);

	Map<String, Long> getReactionCountMap(Post post);

	Map<String, Long> getReactionList(Post post);
}
