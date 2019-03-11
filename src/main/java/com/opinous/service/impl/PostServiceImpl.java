package com.opinous.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.opinous.service.ReactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.Post;
import com.opinous.model.PostDTO;
import com.opinous.model.Room;
import com.opinous.repository.PostRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.PostService;
import com.opinous.service.SecurityService;

@Slf4j
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
		if(post == null) {
			log.error("Cannot save a post with null value");
			return;
		}

		Room room = post.getAnonMap().getRoom();
		room.setUpdateDate(new Date());
		roomRepository.save(room);
		postRepository.save(post);
	}

	@Override
	public List<PostDTO> getPostsByRoom(Room room) {
		if(room == null) {
			log.error("The value room cannot be null while retrieving posts");
			return null;
		}

		return convertToPostDto(postRepository.findByAnonMap_Room(room));
	}

	@Override
	public Post getPost(Long id) {
		if(id == null) {
			log.error("Cannot get post for a null id");
		}

		return postRepository.getOne(id);
	}

	private List<PostDTO> convertToPostDto(final List<Post> posts) {
		List<PostDTO> postsDto = new LinkedList<>();
		for(Post post : posts) {
			PostDTO postDto = new PostDTO();
			copyFromPostToPostDto(post, postDto);
			postsDto.add(postDto);
		}
		return postsDto;
	}

	private void copyFromPostToPostDto(final Post post, PostDTO postDto) {
		postDto.setId(post.getId());
		postDto.setAnonMap(post.getAnonMap());
		postDto.setCreateDate(post.getCreateDate());
		postDto.setText(post.getText());
		postDto.setUpdateDate(post.getUpdateDate());
		postDto.setReactionCounts(reactionService.getReactionCountMap(post));
		postDto.setReactionList(reactionService.getReactionList(post));
	}
}
