package com.opinous.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.opinous.model.Post;
import com.opinous.model.PostDTO;
import com.opinous.model.Room;

@Service
public interface PostService {

	void savePost(Post post);

	List<PostDTO> getPostsByRoom(Room room);
	
	Post getPost(Long id);
}
