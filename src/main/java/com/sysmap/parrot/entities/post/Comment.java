package com.sysmap.parrot.entities.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

	private UUID id;
	private UUID userId;
	private String content;
	private List<Like> likes;

	public Comment(UUID userId, String content, List<Like> likes) {
		setId();
		this.userId = userId;
		this.content = content;
		this.likes = likes;
	}

	public void setId() {
		this.id = UUID.randomUUID();
	}
}
