package com.opinous.service.impl;

import com.opinous.model.Room;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private AnonMapRepository anonMapRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public void createRoom(Room room) {
        roomRepository.save(room);
    }
}
