package com.opinous.service;

import com.opinous.model.Post;
import com.opinous.model.Reaction;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;

import java.util.Map;

@Service
public interface ReactionService {
	public void addReaction(ReactionType reactionType, String postId);
	public void removeReaction(ReactionType reactionType, String postId);
	public boolean exists(Post post, ReactionType reactionType);
	public Reaction getReaction(Post post, ReactionType reactionType);
	public long getReactionCount(Post post, ReactionType reactionType);
	public Map<String, Long> getReactionCountMap(Post post);
	public Map<String, Long> getReactionList(Post post);
}
