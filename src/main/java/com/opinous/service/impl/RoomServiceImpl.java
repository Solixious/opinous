package com.opinous.service.impl;

import com.opinous.constants.AppConfigDefaultValues;
import com.opinous.constants.AppConfigKeys;
import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.AppConfigService;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private AnonMapRepository anonMapRepository;

	@Autowired
	private AnonymousUserService anonymousUserService;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AppConfigService appConfigService;

	@Override
	public void createRoom(Room room) {
		AnonymousUser anonymousUser = anonymousUserService.generateAnonymousUser(room);
		User user = userRepository.findByUsername(securityService.findLoggedInUsername());
		AnonMap anonMap = new AnonMap();
		anonMap.setAnonymousUser(anonymousUser);
		anonMap.setUser(user);
		anonMap.setRoom(room);
		roomRepository.save(room);
		anonMapRepository.save(anonMap);

		room.setCreator(anonMap);
		roomRepository.save(room);
	}

	@Override
	public Room getRoomById(Long roomId) {
		return roomRepository.getOne(roomId);
	}

	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll(Sort.by(Sort.Order.desc("id")));
	}

	@Override
	public List<Room> getRooms(int page) {
		int size = Integer.parseInt(appConfigService.getAppConfig(AppConfigKeys.HOME_PAGE_ROOM_COUNT,
				AppConfigDefaultValues.HOME_PAGE_ROOM_COUNT_DEFAULT_VALUE));
		return roomRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("updateDate")))).getContent();
	}
}
