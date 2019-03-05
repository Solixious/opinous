package com.opinous.service;

import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;

@Service
public interface AnonMapService {
	public AnonMap saveAnonMap(AnonymousUser anonymousUser, User user, Room room);
	public AnonMap getAnonMapByRoomAndUser(Room room, User user);
}
