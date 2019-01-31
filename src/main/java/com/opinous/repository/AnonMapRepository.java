package com.opinous.repository;

import com.opinous.model.AnonMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnonMapRepository extends JpaRepository<AnonMap, Long> {

    public List<AnonMap> findByUserId(Long userId);

    public List<AnonMap> findByRoomId(Long roomId);

    public AnonMap findByRoomIdAndUserId(Long roomId, Long userId);
}
