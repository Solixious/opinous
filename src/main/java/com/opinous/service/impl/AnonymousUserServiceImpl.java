package com.opinous.service.impl;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.AnonMapRepository;
import com.opinous.repository.AnonymousUserRepository;
import com.opinous.service.AnonymousUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AnonymousUserServiceImpl implements AnonymousUserService {

    @Autowired
    private AnonymousUserRepository anonymousUserRepository;

    @Autowired
    private AnonMapRepository anonMapRepository;

    @Override
    public AnonymousUser getAnonymousUser(User user, Room room) {
        return anonymousUserRepository.findById(anonMapRepository.findByRoomIdAndUserId(
                room.getId(), user.getId()).getAnonymousUserId()).get();
    }

    @Override
    public AnonymousUser generateAnonymousUser(Room room) {
        List<AnonMap> anonMaps = null;
        List<AnonymousUser> anonymousUsers = anonymousUserRepository.findAll();

        if(room.getId() != null) {
            anonMaps = anonMapRepository.findByRoomId(room.getId());

            for (AnonMap anonMap : anonMaps) {
                Long id = anonMap.getAnonymousUserId();
                for (AnonymousUser anonymousUser : anonymousUsers) {
                    if (anonymousUser.getId() == id) {
                        anonymousUsers.remove(anonymousUser);
                        break;
                    }
                }
            }
        }
        AnonymousUser randomAnonymosUser = anonymousUsers.get(
                new Random().nextInt(anonymousUsers.size()));

        return randomAnonymosUser;
    }
}
