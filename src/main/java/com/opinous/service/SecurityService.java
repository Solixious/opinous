package com.opinous.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {

	/**
	 * @return The username of the person who's logged in
	 */
	String findLoggedInUsername();

	/**
	 * @param username Username
	 * @param password Password corresponding to the given username
	 */
	void autologin(String username, String password);

	/**
	 * @param role The name of the role
	 * @return True if the logged in username has the provided role
	 */
	boolean hasRole(String role);

	/**
	 * @return True if logged in user has admin role
	 */
	boolean isAdmin();

	/**
	 * @return True if logged in user has user role
	 */
	boolean isUser();

	/**
	 * @return True if logged in user has moderator role
	 */
	boolean isModerator();
}
