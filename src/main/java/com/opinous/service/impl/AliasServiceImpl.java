package com.opinous.service.impl;

import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opinous.model.Alias;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AliasRepository;
import com.opinous.service.AliasService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AliasServiceImpl implements AliasService {

	@Autowired
	private AliasRepository aliasRepository;

	@Override
	public Alias getAliasByRoomAndUser(final Room room, final User user) {
		PreCondition.checkNotNull(room, "room");
		PreCondition.checkNotNull(user, "user");
		final Alias alias = aliasRepository.findByRoomAndUser(room, user);
		if(alias == null) {
			log.error("Could not find alias entry for room: {} and user: {}", room, user);
		}
		return alias;
	}

	@Override
	public Alias saveAlias(final AnonymousUser anonymousUser, final User user, final Room room) {
		PreCondition.checkNotNull(anonymousUser, "anonymousUser");
		PreCondition.checkNotNull(user, "user");
		PreCondition.checkNotNull(room, "room");
		final Alias alias = aliasRepository.save(new Alias(room, anonymousUser, user));
		if(alias == null) {
			log.error("Failed to save the anoMap to the database. "
				+ "anonymousUser: {}, user: {}, room: {}", anonymousUser, user, room);
		}
		return alias;
	}

	@Override
	public List<Alias> getAliasInRoom(final Room room) {
		PreCondition.checkNotNull(room, "room");
		final List<Alias> aliases = aliasRepository.findByRoom(room);
		if(aliases == null || aliases.size() == 0) {
			log.error("Failed to retrieve alias data from the data base."
				+ "room: {}, aliases: {}", room, aliases);
		}
		return aliases;
	}

	@Override
	public List<AnonymousUser> getAnonymousUsersInRoom(final Room room) {
		PreCondition.checkNotNull(room, "room");
		final List<AnonymousUser> anonymousUsers =
			getAliasInRoom(room).stream().map(Alias::getAnonymousUser).collect(Collectors.toList());

		if(anonymousUsers == null || anonymousUsers.size() == 0) {
			log.error("Failed to retrieve anonymousUsers' data from the data base."
				+ "room: {}, anonymousUsers: {}", room, anonymousUsers);
		}
		return anonymousUsers;
	}
}
