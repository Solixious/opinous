package com.opinous.repository;

import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.common.CustomPagingAndSortRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostRepository extends CustomPagingAndSortRepository<Post, Long> {
	List<Post> findByAlias_Room(Room room, Sort sort);
	Long countByAlias_Room(Room room);
	List<Post> findByAlias_User(User user);
}
