package com.sysmap.parrot.mappers.friend;

import com.sysmap.parrot.entities.friend.Friend;
import com.sysmap.parrot.entities.friend.dto.FriendResponse;
import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper {

	FriendResponse mappingFriend (Friend friend);
}
