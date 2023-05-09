package com.sysmap.parrot.entities.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "posts")
public class Post {

	@Id
	private UUID id;
	private UUID idAuthor;
	private String nameAuthor;
	private String content;
	private String media;
	private List<Like> likes;
	private List<Comment> commentList;


	public Post (UUID idAuthor, String nameAuthor, String content, String media, List<Like> likes, List<Comment> commentList) {
		this.id = UUID.randomUUID();
		this.idAuthor = idAuthor;
		this.nameAuthor = nameAuthor;
		this.content = content;
		this.media = media;
		this.likes = likes;
		this.commentList = commentList;
	}
}
