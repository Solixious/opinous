package com.opinous.service;

import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void save(User user);
    public User findByUsername(String username);
    public User findByEmail(String email);
}
