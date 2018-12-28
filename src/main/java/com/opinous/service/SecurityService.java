package com.opinous.service;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    public String findLoggedInUsername();
    public void autologin(String username, String password);
}
