package com.sysmap.parrot.entities.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	private UUID id;
	private UUID idUser;
	private String nameUser;
	private Date date;
	private String content;
	private String media;
	private Like likes;
	private List<Comment> commentList;


	public Post (UUID idUser, String nameUser, Date date, String content, String media, Like likes, List<Comment> commentList) {
		this.id = UUID.randomUUID();
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.date = date;
		this.content = content;
		this.media = media;
		this.likes = likes;
		this.commentList = commentList;
	}
}
