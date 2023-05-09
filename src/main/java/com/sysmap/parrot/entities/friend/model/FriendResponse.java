package com.sysmap.parrot.entities.friend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class FriendResponse {

	private List<UUID> followingId;
	private List<UUID> followersId;
	private Integer countFollowing;
	private Integer countfollowers;

	public FriendResponse(List<UUID> followingId, List<UUID> followersId) {
		this.followingId = followingId;
		this.followersId = followersId;
		setCountFollowing(followingId);
		setCountfollowers(followersId);
	}


	public void setCountFollowing(List<UUID> followingId) {
		this.countFollowing = followingId.size();
	}

	public void setCountfollowers(List<UUID> followersId) {
		this.countfollowers = followersId.size();
	}
}
