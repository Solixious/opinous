package com.opinous.service.impl;

import com.opinous.model.Follow;
import com.opinous.model.User;
import com.opinous.repository.FollowRepository;
import com.opinous.service.FollowService;
import com.opinous.service.UserService;
import com.opinous.utils.PreCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public void follow(final User user) {
        PreCondition.checkNotNull(user, "user");
        final User currentUser = userService.getLoggedInUser();
        final Follow follow = new Follow();
        follow.setFollower(currentUser);
        follow.setFollowing(user);
        followRepository.save(follow);
    }

    @Override
    public void unfollow(final User user) {
        PreCondition.checkNotNull(user, "user");
        final Follow follow = followRepository.findByFollowerAndFollowing(
            userService.getLoggedInUser(), user);
        followRepository.delete(follow);
    }

    @Override
    public List<User> getFollowers(final User user) {
        PreCondition.checkNotNull(user, "user");
        return followRepository.findByFollower(user).stream().map(
            u -> u.getFollower()).collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowing(final User user) {
        PreCondition.checkNotNull(user, "user");
        return followRepository.findByFollowing(user).stream().map(
            u -> u.getFollowing()).collect(Collectors.toList());
    }
}
