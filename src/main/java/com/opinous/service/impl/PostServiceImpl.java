package com.opinous.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.opinous.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.enums.ReactionType;
import com.opinous.model.Post;
import com.opinous.model.PostDTO;
import com.opinous.model.Room;
import com.opinous.repository.PostRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.PostService;
import com.opinous.service.SecurityService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ReactionService reactionService;
	
	@Override
	public void savePost(Post post) {
		Room room = post.getAnonMap().getRoom();
		room.setUpdateDate(new Date());
		roomRepository.save(room);
		postRepository.save(post);
	}

	@Override
	public List<PostDTO> getPostsByRoom(Room room) {
		List<PostDTO> postsDto = convertToPostDto(postRepository.findByAnonMap_Room(room));
		return postsDto;
	}

	private List<PostDTO> convertToPostDto(List<Post> posts) {
		List<PostDTO> postsDto = new LinkedList<>();
		for(Post post : posts) {
			PostDTO postDto = new PostDTO();
			postDto.setId(post.getId());
			postDto.setAnonMap(post.getAnonMap());
			postDto.setCreateDate(post.getCreateDate());
			postDto.setText(post.getText());
			postDto.setUpdateDate(post.getUpdateDate());
			postDto.setReactionCounts(reactionService.getReactionCountMap(post));
			postDto.setLiked(reactionService.getReactionList(post));
			postsDto.add(postDto);
		}
		return postsDto;
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.getOne(id);
	}
}
