package com.sysmap.parrot.services.post.comment;

import com.sysmap.parrot.data.PostRepository;
import com.sysmap.parrot.entities.post.Comment;
import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.comment.CommentRequest;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.comment.UpdateComentRequest;
import com.sysmap.parrot.mappers.post.PostMapper;
import com.sysmap.parrot.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

	public static final String COMENTARIO_NAO_ENCONTRADO = "Comentário não encontrado";

	@Autowired
	PostService postService;

	@Autowired
	PostRepository repository;

	@Autowired
	PostMapper mapper;

	public PostResponse createComment(CommentRequest request, UUID idPost) {
		Post post = postService.findPostWithoutMapper(idPost);

		Comment comment = new Comment();
		comment.setId();
		comment.setUserId(request.getIdUser());
		comment.setContent(request.getComment());

		post.getCommentList().add(comment);

		repository.save(post);

		return mapper.mapToPostResponse(post);
	}

	public PostResponse updateComment(UpdateComentRequest request, UUID idPost, UUID idComment) {
		Post post = postService.findPostWithoutMapper(idPost);

		List<Comment> commentList = post.getCommentList();

		Optional<Comment> commentOptional = commentList.stream().filter(c -> c.getId().equals(idComment)).findFirst();

		if (commentOptional.isEmpty()){
				throw new NoSuchElementException(COMENTARIO_NAO_ENCONTRADO);
		}

		Comment comment = commentOptional.get();
		comment.setContent(request.getComment());
		post.setCommentList(commentList);
		repository.save(post);

		return mapper.mapToPostResponse(post);
	}

	public void deleteComment(UUID idPost, UUID idComment) {
		Post post = postService.findPostWithoutMapper(idPost);

		List<Comment> comments = post.getCommentList();
		Optional<Comment> commentOptional = comments.stream().filter(c -> c.getId().equals(idComment)).findFirst();

		if (commentOptional.isEmpty()){
			throw new NoSuchElementException(COMENTARIO_NAO_ENCONTRADO);
		}

		comments.remove(commentOptional.get());
		repository.save(post);

	}


}
