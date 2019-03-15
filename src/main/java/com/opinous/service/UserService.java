package com.opinous.service;

import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	void saveUser(User user);

	void updateUser(User user);

	void saveUser(User user, RoleConst[] roles);

	void copyNecessaryUpdates(User from, User to);

	User findByUsername(String username);

	User findByEmail(String email);

	User getLoggedInUser();
}
