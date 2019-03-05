package com.opinous.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.opinous.model.AnonMap;
import com.opinous.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("select p from Post p where anonMap in :anonMaps order by createDate desc")
	public List<Post> findByAnonMapIn(List<AnonMap> anonMaps);
}
