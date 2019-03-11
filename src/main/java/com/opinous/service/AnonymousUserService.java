package com.opinous.service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AnonymousUserService {

	AnonymousUser getAnonymousUser(User user, Room room);

	AnonMap getAnonMap(User user, Room room);

	AnonymousUser generateAnonymousUser(Room room);
}
