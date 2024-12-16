package com.codeWithMe.chat.repositories;

import com.codeWithMe.chat.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room,String> {

	//getRoom using Id
	Room findByRoomId(String roomId);
}
