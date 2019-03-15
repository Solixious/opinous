package com.opinous.service.impl;

import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonymousUserRepository;
import com.opinous.service.AnonMapService;
import com.opinous.service.AnonymousUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AnonymousUserServiceImpl implements AnonymousUserService {

	@Autowired
	private AnonymousUserRepository anonymousUserRepository;

	@Autowired
	private AnonMapService anonMapService;

	@Override
	public AnonymousUser getAnonymousUser(final User user, final Room room) {
		if(user == null || room == null) {
			log.error("The value of user and room should not be null. "
				+ "user: {}, room: {}", user, room);
			return null;
		}

		return anonMapService.getAnonMapByRoomAndUser(room, user).getAnonymousUser();
	}

	@Override
	public AnonymousUser generateAnonymousUser(final Room room) {
		if(room == null) {
			log.error("The room should not be null in generateAnonymousUser.");
			return null;
		}

		final List<AnonymousUser> anonymousUsers = anonymousUserRepository.findAll();
		if(room.getId() != null) {
			final List<AnonymousUser> anonymousUsersTaken = anonMapService.getAnonymousUsersInRoom(room);
			anonymousUsers.removeAll(anonymousUsersTaken);
		}

		if(anonymousUsers.size() == 0) {
			log.error("There aren't any anonymous users left for room: {}", room);
			return null;
		}

		return anonymousUsers.get(new Random().nextInt(anonymousUsers.size()));
	}

	@Override
	public AnonymousUser findByName(final String name) {
		if(name == null) {
			log.error("Cannot find entry for null value");
		}
		return anonymousUserRepository.findByName(name);
	}


	@Override
	public AnonymousUser findById(final Long id) {
		if(id == null) {
			log.error("Cannot find entry for null value");
		}
		return anonymousUserRepository.findById(id).get();
	}

	@Override
	public List<AnonymousUser> findAll() {
		return anonymousUserRepository.findAll();
	}

	@Override
	public void saveAnonymousUser(final AnonymousUser anonymousUser) {
		if(anonymousUser == null) {
			log.error("Cannot save null anonymous user value to database.");
			return;
		}
		anonymousUserRepository.save(anonymousUser);
	}
}
