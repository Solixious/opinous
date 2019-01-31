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
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (roleRepository.count() == 0) {
            populateDefaultRoles();
            user.setRoles(new HashSet<>(roleRepository.findAll()));
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(RoleConst.USER_ROLE.toString()));
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user, RoleConst[] roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (roleRepository.count() == 0) {
            populateDefaultRoles();
        }

        HashSet<Role> roleSet = new HashSet<>();

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
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void copyNecessaryUpdates(User from, User to) {
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
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
