package com.codeWithMe.chat.services;

import com.codeWithMe.chat.entities.Message;
import com.codeWithMe.chat.entities.Room;
import com.codeWithMe.chat.payload.MessageRequest;
import com.codeWithMe.chat.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {
	private final RoomRepository roomRepository;

	public ChatService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Message sendMessage(String roomId, MessageRequest req) {
		// Fetch the existing room by its ID
		Room room = roomRepository.findByRoomId(roomId);

		if (room == null) {
			throw new RuntimeException("Room not found");
		}

		// Create a new message
		Message message = new Message(req.getSender(), req.getContent());
		message.setContent(req.getContent());
		message.setSender(req.getSender());
		message.setTimeStamp(LocalDateTime.now());

		// Add the message to the room and save
		room.getMessages().add(message);
		roomRepository.save(room);

		return message;
	}
}
