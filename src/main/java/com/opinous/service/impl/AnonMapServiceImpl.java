package com.opinous.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.service.AnonMapService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AnonMapServiceImpl implements AnonMapService {

	@Autowired
	private AnonMapRepository anonMapRepository;

	@Override
	public AnonMap getAnonMapByRoomAndUser(final Room room, final User user) {

		if(room == null || user == null) {
			log.error("The value of Room and User should not be null to getAnonMapByRoomAndUser. "
				+ "room: {}, user: {}", room, user);
			return null;
		}

		final AnonMap anonMap = anonMapRepository.findByRoomAndUser(room, user);
		if(anonMap == null) {
			log.error("Could not find anonMap entry for room: {} and user: {}", room, user);
		}
		return anonMap;
	}

	@Override
	public AnonMap saveAnonMap(final AnonymousUser anonymousUser, final User user, final Room room) {

		if(anonymousUser == null || user == null || room == null) {
			log.error("The value of AnonymousUser, User and Room cannot be null to saveAnonMap. "
				+ "anonymousUser: {}, user: {}, room: {}", anonymousUser, user, room);
			return null;
		}

		AnonMap anonMap = new AnonMap();
		anonMap.setAnonymousUser(anonymousUser);
		anonMap.setUser(user);
		anonMap.setRoom(room);
		anonMap = anonMapRepository.save(anonMap);

		if(anonMap == null) {
			log.error("Failed to save the anoMap to the database. "
				+ "anonymousUser: {}, user: {}, room: {}", anonymousUser, user, room);
		}
		return anonMap;
	}

	@Override
	public List<AnonMap> getAnonMapsInRoom(Room room) {
		if(room == null) {
			log.error("The room should not be null in getAnonMapsInRoom.");
			return null;
		}
		List<AnonMap> anonMaps = anonMapRepository.findByRoom(room);
		if(anonMaps == null || anonMaps.size() == 0) {
			log.error("Failed to retrieve anonMap data from the data base."
				+ "room: {}, anonMaps: {}", room, anonMaps);
		}
		return anonMaps;
	}

	@Override
	public List<AnonymousUser> getAnonymousUsersInRoom(Room room) {
		if(room == null) {
			log.error("The room should not be null in getAnonMapsInRoom.");
			return null;
		}
		List<AnonymousUser> anonymousUsers =
			getAnonMapsInRoom(room).stream().map(r -> r.getAnonymousUser()).collect(Collectors.toList());

		if(anonymousUsers == null || anonymousUsers.size() == 0) {
			log.error("Failed to retrieve anonymousUsers' data from the data base."
				+ "room: {}, anonymousUsers: {}", room, anonymousUsers);
		}
		return anonymousUsers;
	}
}
