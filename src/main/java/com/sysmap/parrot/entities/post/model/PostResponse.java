package com.sysmap.parrot.entities.post.model;

import com.sysmap.parrot.entities.post.Comment;
import com.sysmap.parrot.entities.post.Like;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Slf4j
@ToString
public class PostResponse {

	private UUID id;
	private UUID idAuthor;
	private String nameUser;
	private String content;
	private String media;
	private List<Like> likes;
	private List<Comment> commentList;
	private Integer numberLikes;
	private Integer numberComments;

	public PostResponse(UUID id, UUID idAuthor, String nameUser, String content, String media, List<Like> likes, List<Comment> commentList) {
		this.id = id;
		this.idAuthor = idAuthor;
		this.nameUser = nameUser;
		this.content = content;
		this.media = media;
		this.likes = likes;
		this.commentList = commentList;
		setNumberLikes(likes);
		setNumberComments(commentList);
	}

	public void setNumberLikes(List<Like> likes) {
		this.numberLikes = likes.size();
	}

	public void setNumberComments(List<Comment> numberComments) {
		this.numberComments = numberComments.size();
	}
}
