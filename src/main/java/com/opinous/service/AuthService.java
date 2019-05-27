package com.opinous.service;

import com.opinous.model.Role;
import com.opinous.request.AuthenticateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {
    String generateToken(AuthenticateRequest authenticateRequest);
    void invalidateToken(String token);
    List<Role> getRoles(String token);
}
