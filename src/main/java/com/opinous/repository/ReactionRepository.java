package com.opinous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Post;
import com.opinous.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
	public Long countByPostAndReactionType(Post post, String reactionType);
	public Long countByPostAndReactionTypeAndAnonMap_User_Username(Post post, String reactionType, String username);
	public Reaction findByPostAndReactionTypeAndAnonMap_User_Username(Post post, String reactionType, String username);
}
