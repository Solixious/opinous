package com.opinous.service.impl;

import com.opinous.constants.AppConfigDefaultValues;
import com.opinous.constants.AppConfigKeys;
import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.Alias;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.model.dto.RoomDTO;
import com.opinous.model.User;
import com.opinous.repository.AliasRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.AppConfigService;
import com.opinous.service.PostService;
import com.opinous.service.RoomService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.opinous.service.UserService;
import com.opinous.utils.PreCondition;
import com.opinous.utils.PrettyTimeUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private AliasRepository aliasRepository;

	@Autowired
	private AnonymousUserService anonymousUserService;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	@Override
	public void createRoom(final Room room) throws RoomOverloadedException {
		PreCondition.checkNotNull(room, "room");
		final Alias alias = new Alias(room, anonymousUserService.generateAnonymousUser(room),
			userService.getLoggedInUser());
		roomRepository.save(room);
		aliasRepository.save(alias);

		room.setCreator(alias);
		roomRepository.save(room);
	}

	@Override
	public Room getRoomById(final Long roomId) {
		PreCondition.checkNotNull(roomId, "roomId");
		return roomRepository.findOne(roomId);
	}

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll(Sort.by(Sort.Order.desc("updateDate")));
	}

	@Override
	public Page<Room> getRooms(final int page) {
		if(page < 0) {
			log.error("A positive page number is required to fetch the page of rooms.");
			return null;
		}

		return roomRepository.findAll(PageRequest.of(page, Integer.parseInt(appConfigService
				.getAppConfig(AppConfigKeys.HOME_PAGE_ROOM_COUNT,
					AppConfigDefaultValues.HOME_PAGE_ROOM_COUNT_DEFAULT_VALUE, "No. of rooms in "
						+ "home page")),
			Sort.by(Sort.Order.desc("updateDate"))));
	}

	@Override
	public Set<Room> getDistinctRoomsFromPosts(List<Post> posts) {
		PreCondition.checkNotNull(posts, "posts");
		return  posts.stream().map(RoomServiceImpl::getRoomFromPost).collect(Collectors.toSet());
	}

	@Override
	public List<Room> getRoomsForUser(User user) {
		PreCondition.checkNotNull(user, "user");
		return roomRepository.findByCreator_User(user);
	}
	
	@Override
	public List<RoomDTO> convertToRoomDTO(List<Room> rooms) {
		return rooms.stream().map(r -> convertToRoomDTO(r)).collect(Collectors.toList());
	}
	
	@Override
	public RoomDTO convertToRoomDTO(Room room) {
		final RoomDTO roomDto = new RoomDTO();
		roomDto.setId(room.getId());
		roomDto.setCreator(room.getCreator());
		roomDto.setTitle(room.getTitle());
		roomDto.setDescription(room.getDescription());
		roomDto.setCreateDate(room.getCreateDate());
		roomDto.setUpdateDate(room.getUpdateDate());
		roomDto.setUpdatedTimeAgo(PrettyTimeUtils.convertToTimeAgo(room.getUpdateDate()));
		roomDto.setCreatedTimeAgo(PrettyTimeUtils.convertToTimeAgo(room.getCreateDate()));
		roomDto.setParticipantCount((int) aliasRepository.countByRoom(room).longValue());
		roomDto.setPostCount((int) postService.countPostsByRoom(room).longValue());
		return roomDto;
	}

	private static Room getRoomFromPost(final Post p) {
		return p.getAlias().getRoom();
	}
}
