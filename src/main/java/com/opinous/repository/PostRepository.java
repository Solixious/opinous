package com.opinous.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.AnonMap;
import com.opinous.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>  {
	public List<Post> findByAnonMapIn(List<AnonMap> anonMaps);
}
