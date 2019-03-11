package com.opinous.service;

import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;

import java.util.List;

@Service
public interface AnonMapService {

	AnonMap saveAnonMap(AnonymousUser anonymousUser, User user, Room room);

	AnonMap getAnonMapByRoomAndUser(Room room, User user);

	List<AnonMap> getAnonMapsInRoom(Room room);

	List<AnonymousUser> getAnonymousUsersInRoom(Room room);
}
