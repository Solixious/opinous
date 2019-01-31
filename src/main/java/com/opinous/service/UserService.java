package com.opinous.service;

import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void saveUser(User user);

    public void updateUser(User user);

    public void saveUser(User user, RoleConst[] roles);

    public void copyNecessaryUpdates(User from, User to);

    public User findByUsername(String username);

    public User findByEmail(String email);
}
