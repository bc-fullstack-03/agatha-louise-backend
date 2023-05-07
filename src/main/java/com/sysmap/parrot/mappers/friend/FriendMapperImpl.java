package com.sysmap.parrot.mappers.friend;

import com.sysmap.parrot.entities.friend.Friend;
import com.sysmap.parrot.entities.friend.dto.FriendResponse;
import org.springframework.stereotype.Component;

@Component
public class FriendMapperImpl implements FriendMapper {

	@Override
	public FriendResponse mappingFriend(Friend friend) {
		return new FriendResponse(friend.getFollowingId(),friend.getFollowersId());
	}
}
