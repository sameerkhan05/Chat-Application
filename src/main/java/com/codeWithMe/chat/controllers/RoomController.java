package com.codeWithMe.chat.controllers;

import com.codeWithMe.chat.entities.Room;
import com.codeWithMe.chat.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {
		Room room = roomService.createRoom(roomId);
		if (room == null) {
			return ResponseEntity.badRequest().body("Room already Exists");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}
}
