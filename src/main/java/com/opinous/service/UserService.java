package com.opinous.service;

import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	/**
	 * @param user The User object to be persisted in the database
	 */
	void saveUser(User user);

	/**
	 * @param user The User object to be updated in the database
	 */
	void updateUser(User user);

	/**
	 * @param user The user object to be persisted in the database
	 * @param roles The list of roles the user would initially have
	 */
	void saveUser(User user, RoleConst[] roles);

	/**
	 * @param from The source user objecr
	 * @param to The destination user object where details are being copied to
	 */
	void copyNecessaryUpdates(User from, User to);

	/**
	 * @param username The username being seeked
	 * @return The User object corresponding to the username provided
	 */
	User findByUsername(String username);

	/**
	 * @param email The email of the user being seeked
	 * @return The User object corresponding to the email id provided
	 */
	User findByEmail(String email);

	/**
	 * @return The User object of the logged in user
	 */
	User getLoggedInUser();

	/**
	 * @param roles List of roles
	 * @return User list corresponding to the provided roles
	 */
	List<User> findBySpecificRoles(List<String> roles);

	/**
	 * @param id The id of the User being seeked
	 * @return The User object corresponding to the provided user id
	 */
	User findById(Long id);

	/**
	 * @param user The User object to be removed from the database
	 */
	void delete(User user);

	/**
	 * @return Get list of all the User
	 */
	List<User> findAll();

	/**
	 * @param query The search query
	 * @return The list of user whose username contains the search query
	 */
	List<User> search(String query);
}
