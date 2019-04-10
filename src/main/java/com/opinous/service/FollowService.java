package com.opinous.service;

import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {

    /**
     * @param user The User object the person logged in wants to follow
     */
    void follow(User user);

    /**
     * @param user The User object the person logged in wants to unfollow
     */
    void unfollow(User user);

    /**
     * @param user The User object for whom we want the list of followers
     * @return The list of User who are followers of the provided User object
     */
    List<User> getFollowers(User user);

    /**
     * @param user The User object for whom we want the list of User s/he's following
     * @return The list of User who are being follower by the User object provided
     */
    List<User> getFollowing(User user);

    /**
     * @param follower User object indicating follower
     * @param following User Object indicating the one being followed
     * @return True if the there is a combination of follower and following
     */
    boolean exists(User follower, User following);

    /**
     * @param user The User object denoting the person being followed
     * @return True if the one logged in is following the person provided
     */
    boolean isFollowing(User user);

    /**
     * @param user The User object denoting the person who is following
     * @return True if the one logged in is being followed the person provided
     */
    boolean isFollower(User user);

    /**
     * @param user The User object for whom we want the number of followers
     * @return The number of followers of the provided User
     */
    Long getFollowerCount(User user);

    /**
     * @param user The User object for whom we want the number of people s/he's following
     * @return The number of people being followed by the provided User
     */
    Long getFollowingCount(User user);
}
