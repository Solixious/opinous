package com.opinous.service.impl;

import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonymousUserRepository;
import com.opinous.service.AnonMapService;
import com.opinous.service.AnonymousUserService;
import com.opinous.utils.PreCondition;
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
		PreCondition.checkNotNull(user, "user");
		PreCondition.checkNotNull(room, "room");
		return anonMapService.getAnonMapByRoomAndUser(room, user).getAnonymousUser();
	}

	@Override
	public AnonymousUser generateAnonymousUser(final Room room) {
		PreCondition.checkNotNull(room, "room");
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
		PreCondition.checkNotNull(name, "name");
		return anonymousUserRepository.findByName(name);
	}


	@Override
	public AnonymousUser findById(final Long id) {
		PreCondition.checkNotNull(id, "id");
		return anonymousUserRepository.findById(id).get();
	}

	@Override
	public List<AnonymousUser> findAll() {
		return anonymousUserRepository.findAll();
	}

	@Override
	public void saveAnonymousUser(final AnonymousUser anonymousUser) {
		PreCondition.checkNotNull(anonymousUser, "anonymousUser");
		anonymousUserRepository.save(anonymousUser);
	}
}
