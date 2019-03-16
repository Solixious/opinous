package com.opinous.service;

import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	void saveUser(User user);

	void updateUser(User user);

	void saveUser(User user, RoleConst[] roles);

	void copyNecessaryUpdates(User from, User to);

	User findByUsername(String username);

	User findByEmail(String email);

	User getLoggedInUser();

	List<User> findBySpecificRoles(List<String> roles);

	User findById(Long id);

	void delete(User byId);

	List<User> findAll();

	List<User> search(String query);
}
