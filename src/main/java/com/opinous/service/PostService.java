package com.opinous.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.opinous.model.Post;
import com.opinous.model.PostDTO;
import com.opinous.model.Room;

@Service
public interface PostService {
	public void savePost(Post post);

	public List<PostDTO> getPostsByRoom(Room room);
	
	public Post getPost(Long id);
}
