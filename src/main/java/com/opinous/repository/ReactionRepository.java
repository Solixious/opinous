package com.opinous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Reaction;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
