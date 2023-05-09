package com.sysmap.parrot.entities.friend.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FriendRequest {

	@NotNull(message = "{follow.followerid.not.null}")
	private UUID followerId;

	@NotNull(message = "{follow.followedid.not.null}")
	private UUID followedId;
}
