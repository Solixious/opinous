package com.opinous.repository;

import com.opinous.model.Follow;
import com.opinous.model.User;
import com.opinous.repository.common.CustomPagingAndSortRepository;

import java.util.List;

public interface FollowRepository extends CustomPagingAndSortRepository<Follow, Long> {
    Follow findByFollowerAndFollowing(User follower, User following);
    List<Follow> findByFollower(User follower);
    List<Follow> findByFollowing(User following);
    Long countByFollower(User follower);
    Long countByFollowing(User following);
}
