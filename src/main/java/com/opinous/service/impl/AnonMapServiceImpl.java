package com.opinous.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.service.AnonMapService;

@Service
public class AnonMapServiceImpl implements AnonMapService {

	@Autowired
	private AnonMapRepository anonMapRepository;

	@Override
	public AnonMap getAnonMapByRoomAndUser(Room room, User user) {
		return anonMapRepository.findByRoomAndUser(room, user);
	}

	@Override
	public AnonMap saveAnonMap(AnonymousUser anonymousUser, User user, Room room) {
		AnonMap anonMap = new AnonMap();
		anonMap.setAnonymousUser(anonymousUser);
		anonMap.setUser(user);
		anonMap.setRoom(room);
		anonMapRepository.save(anonMap);
		return anonMap;
	}

}
