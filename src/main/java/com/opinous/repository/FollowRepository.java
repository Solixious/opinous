package com.opinous.repository;

import com.opinous.model.Follow;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Follow findByFollowerAndFollowing(User follower, User following);
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    Long countByFollower(User follower);
    Long countByFollowing(User following);
}
