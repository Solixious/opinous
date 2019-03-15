package com.opinous.service.impl;

import com.opinous.enums.RoleConst;
import com.opinous.model.Role;
import com.opinous.model.User;
import com.opinous.repository.RoleRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.SecurityService;
import com.opinous.service.UserService;
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
		if(user == null) {
			log.error("User object should not be null.");
			return;
		}

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
		if(user == null || roles == null) {
			log.error("User object and roles array should not be null. User: {}, Roles: {}",
				user, roles);
		}
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
		if(user == null) {
			log.error("The user object should not be null.");
		}
		userRepository.save(user);
	}

	@Override
	public void copyNecessaryUpdates(final User from, final User to) {
		if(from == null || to == null) {
			log.error("Objects from and to should not be null. from: {}, to: {}", from, to);
			return;
		}

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
		if(username == null) {
			log.error("Username cannot be null while querying the database.");
			return null;
		}
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(final String email) {
		if(email == null) {
			log.error("Username cannot be null while querying the database.");
			return null;
		}
		return userRepository.findByEmail(email);
	}

	@Override
	public User getLoggedInUser() {
		return findByUsername(securityService.findLoggedInUsername());
	}

	@Override
	public List<User> findBySpecificRoles(final List<String> roles) {
		if(roles == null) {
			log.error("List of roles cannot be null while performing this operation.");
			return null;
		}
		return userRepository.findBySpecificRoles(roles);
	}

	@Override
	public User findById(final Long id) {
		if(id == null) {
			log.error("Id cannot be null");
			return null;
		}
		return userRepository.findById(id).get();
	}

	@Override
	public void delete(final User user) {
		userRepository.delete(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> search(final String query) {
		return userRepository.findByUsernameIgnoreCaseContaining(query);
	}
}
