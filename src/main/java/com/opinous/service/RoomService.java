package com.opinous.service;

import com.opinous.exception.RoomOverloadedException;
import com.opinous.model.Post;
import com.opinous.model.Room;
import com.opinous.model.dto.RoomDTO;

import java.util.List;
import java.util.Set;

import com.opinous.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

	/**
	 * @param room The Room object to be persisted in the database
	 */
	void createRoom(Room room) throws RoomOverloadedException;

	/**
	 * @param roomId The room id of the room id to be retrieved
	 * @return The Room object of the corresponding room id
	 */
	Room getRoomById(Long roomId);

	/**
	 * @return Get list of all the Rooms
	 */
	List<Room> getAllRooms();

	/**
	 * @param page The Page number
	 * @return List of paginated Room
	 */
	Page<Room> getRooms(int page);

	/**
	 * @param posts List of Post belonging to various Rooms
	 * @return Distinct list of Room to which the Posts belonged
	 */
	Set<Room> getDistinctRoomsFromPosts(List<Post> posts);

	/**
	 * @param user The User object
	 * @return The List of Room created by the provided user
	 */
	List<Room> getRoomsForUser(User user);

	/**
	 * @param rooms List of Room
	 * @return List of corresponding CreateRoomRequest
	 */
	List<RoomDTO> convertToRoomDTO(List<Room> rooms);

	/**
	 * @param room The Room object
	 * @return The corresponding CreateRoomRequest object
	 */
	RoomDTO convertToRoomDTO(Room room);
}
