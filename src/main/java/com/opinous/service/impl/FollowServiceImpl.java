package com.opinous.service.impl;

import com.opinous.model.Follow;
import com.opinous.model.User;
import com.opinous.repository.FollowRepository;
import com.opinous.service.FollowService;
import com.opinous.service.UserService;
import com.opinous.utils.PreCondition;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        if(exists(currentUser, user)) {
        	log.warn("The follow entry already exists. currentUser: {}, user: {}", currentUser, user);
        	return;
        }
        followRepository.save(new Follow(currentUser, user));
    }

    @Override
    public void unfollow(final User user) {
        PreCondition.checkNotNull(user, "user");
        final Follow follow = followRepository.findByFollowerAndFollowing(
            userService.getLoggedInUser(), user);
        if(follow != null) {
        	followRepository.delete(follow);
        }
    }

    @Override
    public List<User> getFollowers(final User user) {
        PreCondition.checkNotNull(user, "user");
        return followRepository.findByFollowing(user).stream().map(Follow::getFollower)
            .collect(Collectors.toList());
    }

    @Override
    public List<User> getFollowing(final User user) {
        PreCondition.checkNotNull(user, "user");
        return followRepository.findByFollower(user).stream().map(Follow::getFollowing)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean exists(User follower, User following) {
    	return followRepository.findByFollowerAndFollowing(follower, following) != null;
    }

	@Override
	public boolean isFollowing(User user) {
		PreCondition.checkNotNull(user, "user");
		return exists(userService.getLoggedInUser(), user);
	}

	@Override
	public boolean isFollower(User user) {
		PreCondition.checkNotNull(user, "user");
		return exists(user, userService.getLoggedInUser());
	}
	
	@Override
	public Long getFollowingCount(User user) {
		PreCondition.checkNotNull(user,  "user");
		return followRepository.countByFollower(user);
	}
	
	@Override
	public Long getFollowerCount(User user) {
		PreCondition.checkNotNull(user, "user");
		return followRepository.countByFollowing(user);
	}
}
