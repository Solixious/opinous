package com.opinous.service;

import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.Post;
import com.opinous.model.Reaction;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;

import java.util.Map;

@Service
public interface ReactionService {

	/**
	 * @param reactionType The type of reaction to be added
	 * @param postId The post id of the post to which the reaction should be added
	 */
	void addReaction(ReactionType reactionType, String postId) throws RoomOverloadedException;

	/**
	 * @param reactionType The type of reaction to be removed
	 * @param postId The post id of the post from which the reaction should be removed
	 */
	void removeReaction(ReactionType reactionType, String postId);

	/**
	 * @param post The Post object
	 * @param reactionType The type of reaction we want to check
	 * @return True if the reaction type exists on the given post for the logged in user
	 */
	boolean exists(Post post, ReactionType reactionType);

	/**
	 * @param post The Post object
	 * @param reactionType the type of reaction on the post
	 * @return The Reaction object for the given reaction type and the Post
	 */
	Reaction getReaction(Post post, ReactionType reactionType);

	/**
	 * @param post The Post object
	 * @param reactionType the type of reaction on the post
	 * @return The count for the given reaction type and the Post
	 */
	long getReactionCount(Post post, ReactionType reactionType);

	/**
	 * @param post the Post object
	 * @return The map of each reaction types and their counts for the given Post
	 */
	Map<String, Long> getReactionCountMap(Post post);

	/**
	 * @param post the Post object
	 * @return The map denoting existence of each reaction type for the given Post
	 */
	Map<String, Long> getReactionList(Post post);
}
