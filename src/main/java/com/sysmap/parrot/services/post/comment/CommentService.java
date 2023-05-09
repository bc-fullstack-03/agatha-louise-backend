package com.sysmap.parrot.services.post.comment;

import com.sysmap.parrot.entities.post.model.comment.CommentRequest;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.comment.UpdateComentRequest;

import java.util.UUID;

public interface CommentService {

	PostResponse createComment(CommentRequest request, UUID idPost);

	PostResponse updateComment(UpdateComentRequest request, UUID idPost, UUID idComment );

	void deleteComment(UUID idPost,  UUID idComment);
}
