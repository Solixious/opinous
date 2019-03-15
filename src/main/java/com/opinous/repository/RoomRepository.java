package com.opinous.repository;

import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCreator_User(User user);
}
