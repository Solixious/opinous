package com.opinous.service.impl;

import com.opinous.enums.RoleConst;
import com.opinous.model.Role;
import com.opinous.model.User;
import com.opinous.repository.RoleRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(roleRepository.count() == 0) {
        	for(RoleConst roleConst : RoleConst.values()) {
        		Role role = new Role();
        		role.setName(roleConst.toString());
        		roleRepository.save(role);
                user.setRoles(new HashSet<>(roleRepository.findAll()));
        	}
        } else {
        	Set<Role> roles = new HashSet<>();
        	roles.add(roleRepository.findByName(RoleConst.USER_ROLE.toString()));
        	user.setRoles(roles);
        }
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
