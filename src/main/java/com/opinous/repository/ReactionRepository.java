package com.opinous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Post;
import com.opinous.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

	Long countByPostAndReactionType(Post post, String reactionType);

	Long countByPostAndReactionTypeAndAlias_User_Username(Post post, String reactionType, String username);

	Reaction findByPostAndReactionTypeAndAlias_User_Username(Post post, String reactionType, String username);
}
