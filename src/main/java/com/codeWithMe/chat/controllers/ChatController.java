package com.codeWithMe.chat.controllers;

import com.codeWithMe.chat.entities.Message;
import com.codeWithMe.chat.payload.MessageRequest;
import com.codeWithMe.chat.services.ChatService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController // Changed to @RestController for consistency
public class ChatController {

	private final ChatService chatService;

	public ChatController(ChatService chatService) {
		this.chatService = chatService;
	}

	// WebSocket method for sending messages
	@MessageMapping("/sendMessage/{roomId}")
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(@DestinationVariable String roomId, MessageRequest req) {
		return chatService.sendMessage(roomId, req);
	}
}
