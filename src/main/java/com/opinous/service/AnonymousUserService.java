package com.opinous.service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AnonymousUserService {

    public AnonymousUser getAnonymousUser(User user, Room room);
    
    public AnonMap getAnonMap(User user, Room room);

    public AnonymousUser generateAnonymousUser(Room room);
}
