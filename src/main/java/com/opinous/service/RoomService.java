package com.opinous.service;

import com.opinous.model.Post;
import com.opinous.model.Room;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

	void createRoom(Room room);

	Room getRoomById(Long roomId);

	List<Room> getAllRooms();
	
	Page<Room> getRooms(int page);

	Set<Room> getDistinctRoomsFromPosts(List<Post> posts);
}
