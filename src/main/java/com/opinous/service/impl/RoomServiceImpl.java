package com.opinous.service.impl;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.RoomRepository;
import com.opinous.repository.UserRepository;
import com.opinous.service.AnonymousUserService;
import com.opinous.service.RoomService;
import com.opinous.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private AnonMapRepository anonMapRepository;

    @Autowired
    private AnonymousUserService anonymousUserService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityService securityService;

    @Override
    public void createRoom(Room room) {
        roomRepository.save(room);
        AnonymousUser anonymousUser = anonymousUserService.generateAnonymousUser(room);
        User user = userRepository.findByUsername(securityService.findLoggedInUsername());
        AnonMap anonMap = new AnonMap();
        anonMap.setAnonymousUserId(anonymousUser.getId());
        anonMap.setUserId(user.getId());
        anonMap.setRoomId(room.getId());
        anonMapRepository.save(anonMap);
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.getOne(roomId);
    }
 }
