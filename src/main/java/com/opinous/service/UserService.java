package com.opinous.service;

import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void save(User user);
    public void update(User user);
    public void copyNecessaryUpdates(User from, User to);
    public User findByUsername(String username);
    public User findByEmail(String email);
}
