package com.opinous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Post;
import com.opinous.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

	Long countByPostAndReactionType(Post post, String reactionType);

	Long countByPostAndReactionTypeAndAnonMap_User_Username(Post post, String reactionType, String username);

	Reaction findByPostAndReactionTypeAndAnonMap_User_Username(Post post, String reactionType, String username);
}
