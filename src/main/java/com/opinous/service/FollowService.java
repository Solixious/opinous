package com.opinous.service;

import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    void follow(User user);
    void unfollow(User user);
    List<User> getFollowers(User user);
    List<User> getFollowing(User user);
}
