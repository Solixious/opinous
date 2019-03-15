package com.opinous.service;

import com.opinous.model.AnonMap;
import com.opinous.model.AnonymousUser;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnonymousUserService {

	AnonymousUser getAnonymousUser(User user, Room room);

	AnonymousUser generateAnonymousUser(Room room);

	AnonymousUser findByName(String name);

	AnonymousUser findById(Long id);

	List<AnonymousUser> findAll();

	void saveAnonymousUser(AnonymousUser userForm);
}
