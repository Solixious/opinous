package com.opinous.repository;

import com.opinous.model.AnonMap;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnonMapRepository extends JpaRepository<AnonMap, Long> {

    public List<AnonMap> findByUser(User user);

    public List<AnonMap> findByRoom(Room room);

    public AnonMap findByRoomAndUser(Room room, User user);
}
