package com.opinous.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.PostRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private AnonMapRepository anonMapRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public void savePost(Post post) {
		Room room = post.getAnonMap().getRoom();
		room.setUpdateDate(new Date());
		roomRepository.save(room);
		postRepository.save(post);
	}

	@Override
	public List<Post> getPostsByRoom(Room room) {
		List<AnonMap> anonMaps = anonMapRepository.findByRoom(room);
		return postRepository.findByAnonMapIn(anonMaps);
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.getOne(id);
	}
}
