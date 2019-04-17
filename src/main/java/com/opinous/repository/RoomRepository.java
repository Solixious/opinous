package com.opinous.repository;

import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.common.CustomPagingAndSortRepository;

import java.util.List;

public interface RoomRepository extends CustomPagingAndSortRepository<Room, Long> {
    List<Room> findByCreator_User(User user);
}
