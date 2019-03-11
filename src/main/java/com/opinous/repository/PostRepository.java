package com.opinous.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Post;
import com.opinous.model.Room;

public interface PostRepository extends JpaRepository<Post, Long> {
	public List<Post> findByAnonMap_Room(Room room);
}
