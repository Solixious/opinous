package com.opinous.repository;

import com.opinous.enums.RoleConst;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByUsernameIgnoreCaseContaining(String username);
    User findByEmail(String email);
    List<User> findByEmailIgnoreCaseContaining(String email);

    @Query( "select u from User u inner join u.roles r where r.name in :roles" )
    List<User> findBySpecificRoles(@Param("roles") List<String> roles);
}
