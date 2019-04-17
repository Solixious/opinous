package com.opinous.repository;

import com.opinous.model.Alias;
import com.opinous.model.Room;
import com.opinous.model.User;
import com.opinous.repository.common.CustomPagingAndSortRepository;

import java.util.List;

public interface AliasRepository extends CustomPagingAndSortRepository<Alias, Long> {

	List<Alias> findByUser(User user);

	List<Alias> findByRoom(Room room);
	
	Long countByRoom(Room room);

	Alias findByRoomAndUser(Room room, User user);
}
