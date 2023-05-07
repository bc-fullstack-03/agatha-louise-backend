package com.sysmap.parrot.entities.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "friends")
public class Friend {

	private UUID id;
	private List<UUID> followingId;
	private List<UUID> followersId;
}
