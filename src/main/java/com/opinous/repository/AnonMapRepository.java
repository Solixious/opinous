package com.opinous.repository;

import com.opinous.model.AnonMap;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AnonMapRepository extends JpaRepository<AnonMap, Long> {

    public List<AnonMap> findByUserId(Long userId);
    public List<AnonMap> findByRoomId(Long roomId);
}
