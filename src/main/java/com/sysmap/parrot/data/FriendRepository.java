package com.sysmap.parrot.data;

import com.sysmap.parrot.entities.friend.Friend;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FriendRepository extends MongoRepository<Friend, UUID> {
}
