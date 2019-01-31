package com.opinous.service;

import com.opinous.model.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    public void createRoom(Room room);

    public Room getRoomById(Long roomId);
}
