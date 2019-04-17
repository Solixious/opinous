package com.opinous.repository;

import com.opinous.model.Post;
import com.opinous.model.Reaction;
import com.opinous.repository.common.CustomPagingAndSortRepository;

public interface ReactionRepository extends CustomPagingAndSortRepository<Reaction, Long> {

	Long countByPostAndReactionType(Post post, String reactionType);

	Long countByPostAndReactionTypeAndAlias_User_Username(Post post, String reactionType, String username);

	Reaction findByPostAndReactionTypeAndAlias_User_Username(Post post, String reactionType, String username);
}
