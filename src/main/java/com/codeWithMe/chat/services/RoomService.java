package com.codeWithMe.chat.services;

import com.codeWithMe.chat.entities.Room;
import com.codeWithMe.chat.repositories.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	private final RoomRepository roomRepository;

	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Room createRoom(String roomId) {
		if (roomRepository.findByRoomId(roomId) != null) {
			return null;
		}
		Room room = new Room();
		room.setRoomId(roomId);
		return roomRepository.save(room);
	}
}
