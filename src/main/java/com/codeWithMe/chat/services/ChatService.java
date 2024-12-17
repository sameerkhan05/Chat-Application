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
		Room room = new Room();
		if (room == null) {
			throw new RuntimeException("Room not found");
		}
		Message message = new Message();
		message.setContent(req.getContent());
		message.setSender(req.getSender());
		message.setTimeStamp(LocalDateTime.now());

		room.getMessages().add(message);
		roomRepository.save(room);
		return message;
	}
}
