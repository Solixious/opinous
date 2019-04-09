package com.opinous.repository;

import java.util.List;

import com.opinous.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.opinous.model.Post;
import com.opinous.model.Room;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByAlias_Room(Room room, Sort sort);
	Long countByAlias_Room(Room room);
	List<Post> findByAlias_User(User user);
}
