package com.opinous.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.opinous.model.User;
import com.opinous.service.ReactionService;
import com.opinous.utils.PreCondition;
import com.opinous.utils.PrettyTimeUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.opinous.model.Post;
import com.opinous.model.dto.PostDTO;
import com.opinous.model.Room;
import com.opinous.repository.PostRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.PostService;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ReactionService reactionService;
	
	@Override
	public void savePost(final Post post) {
		PreCondition.checkNotNull(post, "post");
		final Room room = post.getAlias().getRoom();
		room.setUpdateDate(new Date());
		roomRepository.save(room);
		postRepository.save(post);
	}

	@Override
	public List<PostDTO> getPostsByRoom(final Room room) {
		PreCondition.checkNotNull(room, "room");
		return convertToPostDto(postRepository.findByAlias_Room(room, Sort.by("createDate").descending()));
	}

	@Override
	public List<Post> getPostsByUser(final User user) {
		PreCondition.checkNotNull(user, "user");
		return postRepository.findByAlias_User(user);
	}

	@Override
	public Post getPost(final Long id) {
		PreCondition.checkNotNull(id, "id");
		return postRepository.findOne(id);
	}

	@Override
	public Long countPostsByRoom(final Room room) {
		PreCondition.checkNotNull(room, "room");
		return postRepository.countByAlias_Room(room);
	}

	private List<PostDTO> convertToPostDto(final List<Post> posts) {
		PreCondition.checkNotNull(posts, "posts");
		final List<PostDTO> postsDto = new LinkedList<>();
		for(Post post : posts) {
			PostDTO postDto = new PostDTO();
			copyFromPostToPostDto(post, postDto);
			postsDto.add(postDto);
		}
		return postsDto;
	}

	private void copyFromPostToPostDto(final Post post, final PostDTO postDto) {
		postDto.setId(post.getId());
		postDto.setAlias(post.getAlias());
		postDto.setCreateDate(post.getCreateDate());
		postDto.setText(post.getText());
		postDto.setUpdateDate(post.getUpdateDate());
		postDto.setUpdatedTimeAgo(PrettyTimeUtils.convertToTimeAgo(post.getUpdateDate()));
		postDto.setReactionCounts(reactionService.getReactionCountMap(post));
		postDto.setReactions(reactionService.getReactionList(post));
	}
}
