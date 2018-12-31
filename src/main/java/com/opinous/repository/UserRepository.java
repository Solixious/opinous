package com.opinous.repository;

import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameIgnoreCaseContaining(String username);
    User findByEmail(String email);
    List<User> findByEmailIgnoreCaseContaining(String email);
}
