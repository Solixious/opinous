package com.opinous.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.PostRepository;
import com.opinous.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private AnonMapRepository anonMapRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void savePost(Post post) {
		postRepository.save(post);
	}
	
	@Override
	public List<Post> getPostsByRoom(Room room) {
		List<AnonMap> anonMaps = anonMapRepository.findByRoom(room);
        return postRepository.findByAnonMapIn(anonMaps);
	}
}
