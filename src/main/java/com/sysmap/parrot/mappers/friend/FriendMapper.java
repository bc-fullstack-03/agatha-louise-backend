package com.sysmap.parrot.mappers.friend;

import com.sysmap.parrot.entities.friend.Friend;
import com.sysmap.parrot.entities.friend.model.FriendResponse;
import org.mapstruct.Mapper;

@Mapper
public interface FriendMapper {

	FriendResponse mappingFriend (Friend friend);
}
