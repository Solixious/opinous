package com.opinous.service;

import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnonymousUserService {

	/**
	 * @param user The user for whom we want to get the AnonymousUser
	 * @param room The room in which we're searching for the AnonymousUser
	 * @return The AnonymousUser object representing the given user in the provided room
	 */
	AnonymousUser getAnonymousUser(User user, Room room);

	/**
	 * @param room The Room in which we'd like to generate an anonymous user.
	 * @return The AnonymousUser object that isn't used yet in the provided room.
	 */
	AnonymousUser generateAnonymousUser(Room room) throws RoomOverloadedException;

	/**
	 * @param name The name of the AnonymousUser
	 * @return The AnonymousUser object corresponding to the provided name
	 */
	AnonymousUser findByName(String name);

	/**
	 * @param id The id of the AnonymousUser
	 * @return The AnonymousUser object corresponding to the provided id
	 */
	AnonymousUser findById(Long id);

	/**
	 * @return List of all the AnonymousUser present in the system
	 */
	List<AnonymousUser> findAll();

	/**
	 * @param userForm The AnonymousUser object to persist in the database
	 */
	void saveAnonymousUser(AnonymousUser userForm);
}
