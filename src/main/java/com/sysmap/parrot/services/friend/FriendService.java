package com.sysmap.parrot.services.friend;

import com.sysmap.parrot.entities.friend.model.FriendRequest;
import com.sysmap.parrot.entities.friend.model.FriendResponse;

import java.util.UUID;

public interface FriendService {
	FriendResponse followUser(FriendRequest request);

	FriendResponse getListFollowerById(UUID id);

	FriendResponse unfollowUser(FriendRequest request);

	void findUserById(FriendRequest request);
}
