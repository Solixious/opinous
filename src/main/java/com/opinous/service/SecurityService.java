package com.opinous.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {

	String findLoggedInUsername();

	void autologin(String username, String password);

	boolean hasRole(String name);

	boolean isAdmin();

	boolean isUser();

	boolean isModerator();
}
