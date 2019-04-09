package com.opinous.service;

import com.opinous.model.Alias;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;

import java.util.List;

@Service
public interface AliasService {

	Alias saveAlias(AnonymousUser anonymousUser, User user, Room room);

	Alias getAliasByRoomAndUser(Room room, User user);

	List<Alias> getAliasInRoom(Room room);

	List<AnonymousUser> getAnonymousUsersInRoom(Room room);
}
