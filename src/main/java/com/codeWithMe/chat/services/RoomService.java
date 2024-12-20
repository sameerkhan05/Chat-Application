package com.codeWithMe.chat.services;

import com.codeWithMe.chat.entities.Message;
import com.codeWithMe.chat.entities.Room;
import com.codeWithMe.chat.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
	private final RoomRepository roomRepository;

	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Room createRoom(String roomId) {
		if (roomRepository.findByRoomId(roomId) != null) {
			return null; // Return null if the room already exists
		}
		Room room = new Room();
		room.setRoomId(roomId);
		return roomRepository.save(room); // Save and return the room
	}

	public Room getRoom(String roomId) {
		return roomRepository.findByRoomId(roomId);
	}

	public List<Message> getMessages(String roomId, int page, int size) {
		Room room = roomRepository.findByRoomId(roomId);
		if (room == null) {
			return null; // Return null if the room doesn't exist
		}
		List<Message> messages = room.getMessages();
		int start = Math.max(0, messages.size() - (page + 1) * size);
		int end = Math.min(messages.size(), start + size);
		return messages.subList(start, end); // Return paginated messages
	}
}
