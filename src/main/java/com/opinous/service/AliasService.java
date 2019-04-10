package com.opinous.service;

import com.opinous.model.Alias;
import org.springframework.stereotype.Service;

import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;

import java.util.List;

@Service
public interface AliasService {

	/**
	 * @param anonymousUser The AnonymousUser object used by alias
	 * @param user The User object the alias represents
	 * @param room The Room object where the alias will be used
	 * @return The Alias object that is saved in the database after operation
	 */
	Alias saveAlias(AnonymousUser anonymousUser, User user, Room room);

	/**
	 * @param room The Room in which we're searching for an alias
	 * @param user The User who the alias would represent in the room
	 * @return The Alias object who represents the the given user in the provided room
	 */
	Alias getAliasByRoomAndUser(Room room, User user);

	/**
	 * @param room The room in which we're searching for aliases
	 * @return The List of Alias objects present in the room
	 */
	List<Alias> getAliasInRoom(Room room);

	/**
	 * @param room The room in which we're searching for anonymous users
	 * @return The List of AnonymousUser objects present in the room
	 */
	List<AnonymousUser> getAnonymousUsersInRoom(Room room);
}
