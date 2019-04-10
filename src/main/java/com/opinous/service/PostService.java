package com.opinous.service;

import java.util.List;

import com.opinous.model.User;
import org.springframework.stereotype.Service;

import com.opinous.model.Post;
import com.opinous.model.PostDTO;
import com.opinous.model.Room;

@Service
public interface PostService {

	/**
	 * @param post The Post object to be persisted in the database
	 */
	void savePost(Post post);

	/**
	 * @param room The Room object for which we want the list of Post
	 * @return The list of Post in the provided Room
	 */
	List<PostDTO> getPostsByRoom(Room room);

	/**
	 * @param room the Room object for which we want the number of posts
	 * @return The number of posts in the provided Room
	 */
	Long countPostsByRoom(Room room);

	/**
	 * @param user The User object for whom we want the list of Post
	 * @return The List of Post for the given user
	 */
	List<Post> getPostsByUser(User user);

	/**
	 * @param id The post id
	 * @return The Post object for the given post id
	 */
	Post getPost(Long id);
}
