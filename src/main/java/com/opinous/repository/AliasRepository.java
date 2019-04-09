package com.opinous.repository;

import com.opinous.model.Alias;
import com.opinous.model.Room;
import com.opinous.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AliasRepository extends JpaRepository<Alias, Long> {

	List<Alias> findByUser(User user);

	List<Alias> findByRoom(Room room);
	
	Long countByRoom(Room room);

	Alias findByRoomAndUser(Room room, User user);
}
