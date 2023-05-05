package com.sysmap.parrot.entities.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Comment {

	private UUID userId;
	private String content;
	private Like likes;
	private List<Comment> commentsResponse;

}
