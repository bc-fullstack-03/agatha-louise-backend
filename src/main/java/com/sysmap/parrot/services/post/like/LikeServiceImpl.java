package com.sysmap.parrot.services.post.like;

import com.sysmap.parrot.data.PostRepository;
import com.sysmap.parrot.entities.post.Comment;
import com.sysmap.parrot.entities.post.Like;
import com.sysmap.parrot.entities.post.Post;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.like.LikeRequest;
import com.sysmap.parrot.mappers.post.PostMapper;
import com.sysmap.parrot.services.exceptions.DataIntegratyViolationException;
import com.sysmap.parrot.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class LikeServiceImpl implements LikeService {

	public static final String COMENTARIO_NAO_ENCONTRADO = "Comentário não encontrado";
	public static final String DESLIKE_WHITOUT_LIKE = "Você não curtiu esse post";
	public static final String ALREADY_LIKED = "Você já curtiu esse post";


	@Autowired
	PostService postService;

	@Autowired
	PostRepository repository;

	@Autowired
	PostMapper mapper;


	@Override
	public PostResponse likePost(LikeRequest request, UUID idPost) {
		Post post = postService.findPostWithoutMapper(idPost);

		Like like = new Like(request.getIdUser());

		if (post.getLikes().contains(like)){
			throw new DataIntegratyViolationException(ALREADY_LIKED);
		}

		post.getLikes().add(like);

		repository.save(post);

		return mapper.mapToPostResponse(post);
	}

	@Override
	public PostResponse likeComment(LikeRequest request, UUID idPost) {

		Post post = postService.findPostWithoutMapper(idPost);

		Optional<Comment> commentOptional = post.getCommentList().stream().filter(c -> c.getId().equals(request.getIdComment())).findFirst();

		if (commentOptional.isEmpty()){
			throw new NoSuchElementException(COMENTARIO_NAO_ENCONTRADO);
		}

		Comment comment = commentOptional.get();

		Like like = new Like(request.getIdUser());

		if (comment.getLikes() == null){
			comment.setLikes(new ArrayList<>());
		}

		if (comment.getLikes().contains(like)){
			throw new DataIntegratyViolationException(ALREADY_LIKED);
		}

		comment.getLikes().add(like);

		repository.save(post);
		return mapper.mapToPostResponse(post);
	}

	@Override
	public PostResponse deslikePost(LikeRequest request, UUID idPost) {
		Post post = postService.findPostWithoutMapper(idPost);

		Like like = new Like(request.getIdUser());

		if (!post.getLikes().contains(like)){
			throw new NoSuchElementException(DESLIKE_WHITOUT_LIKE);
		}
		
		post.getLikes().remove(like);

		repository.save(post);

		return mapper.mapToPostResponse(post);
	}

	@Override
	public PostResponse deslikeComment(LikeRequest request, UUID idPost) {
		
		Post post = postService.findPostWithoutMapper(idPost);

		Optional<Comment> commentOptional = post.getCommentList().stream().filter(c -> c.getId().equals(request.getIdComment())).findFirst();

		if (commentOptional.isEmpty()){
			throw new NoSuchElementException(COMENTARIO_NAO_ENCONTRADO);
		}

		Comment comment = commentOptional.get();
		Optional<Like> likeOptional = comment.getLikes().stream().filter(l -> l.getUserId().equals(request.getIdUser())).findFirst();

		likeOptional.ifPresent(like -> comment.getLikes().remove(like));

		repository.save(post);
		return mapper.mapToPostResponse(post);
		
	}
}
