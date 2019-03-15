package com.opinous.service.impl;

import com.opinous.enums.RoleConst;
import com.opinous.model.Role;
import com.opinous.model.User;
import com.opinous.repository.RoleRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
import com.opinous.utils.PreCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SecurityService securityService;

	@Override
	public void saveUser(final User user) {
		PreCondition.checkNotNull(user, "user");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (roleRepository.count() == 0) {
			populateDefaultRoles();
			user.setRoles(new HashSet<>(roleRepository.findAll()));
		} else {
			final Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName(RoleConst.USER_ROLE.toString()));
			user.setRoles(roles);
		}
		userRepository.save(user);
	}

	@Override
	public void saveUser(final User user, final RoleConst[] roles) {
		PreCondition.checkNotNull(user, "user");
		PreCondition.checkNotNull(roles, "roles");
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (roleRepository.count() == 0) {
			populateDefaultRoles();
		}

		final HashSet<Role> roleSet = new HashSet<>();
		for (RoleConst role : roles) {
			roleSet.add(roleRepository.findByName(role.toString()));
		}

		user.setRoles(roleSet);
		userRepository.save(user);
	}

	private void populateDefaultRoles() {
		for (RoleConst roleConst : RoleConst.values()) {
			Role role = new Role();
			role.setName(roleConst.toString());
			roleRepository.save(role);
		}
	}

	@Override
	public void updateUser(final User user) {
		PreCondition.checkNotNull(user, "user");
		userRepository.save(user);
	}

	@Override
	public void copyNecessaryUpdates(final User from, final User to) {
		PreCondition.checkNotNull(from, "from");
		PreCondition.checkNotNull(to, "to");
		if (!from.getEmail().equals(to.getEmail()))
			to.setEmail(from.getEmail());
		if (!from.getUsername().equals(to.getUsername()))
			to.setUsername(from.getUsername());
		if (!from.getFirstName().equals(to.getFirstName()))
			to.setFirstName(from.getFirstName());
		if (!from.getLastName().equals(to.getLastName()))
			to.setLastName(from.getLastName());
		if (!from.getPassword().equals("") && from.getPassword().equals(from.getConfirmPassword()))
			to.setPassword(bCryptPasswordEncoder.encode(from.getPassword()));

	}

	@Override
	public User findByUsername(final String username) {
		PreCondition.checkNotNull(username, "username");
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(final String email) {
		PreCondition.checkNotNull(email, "email");
		return userRepository.findByEmail(email);
	}

	@Override
	public User getLoggedInUser() {
		return findByUsername(securityService.findLoggedInUsername());
	}

	@Override
	public List<User> findBySpecificRoles(final List<String> roles) {
		PreCondition.checkNotNull(roles, "roles");
		return userRepository.findBySpecificRoles(roles);
	}

	@Override
	public User findById(final Long id) {
		PreCondition.checkNotNull(id, "id");
		return userRepository.findById(id).get();
	}

	@Override
	public void delete(final User user) {
		PreCondition.checkNotNull(user, "user");
		userRepository.delete(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> search(final String query) {
		PreCondition.checkNotNull(query, "query");
		return userRepository.findByUsernameIgnoreCaseContaining(query);
	}
}
