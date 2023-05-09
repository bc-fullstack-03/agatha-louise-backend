package com.sysmap.parrot.controllers.post;

import com.sysmap.parrot.entities.post.model.PostRequest;
import com.sysmap.parrot.entities.post.model.PostResponse;
import com.sysmap.parrot.entities.post.model.PostUpdateRequest;
import com.sysmap.parrot.entities.post.model.comment.CommentRequest;
import com.sysmap.parrot.entities.post.model.comment.UpdateComentRequest;
import com.sysmap.parrot.entities.post.model.like.LikeRequest;
import com.sysmap.parrot.services.post.PostService;
import com.sysmap.parrot.services.post.comment.CommentService;
import com.sysmap.parrot.services.post.like.LikeService;
import com.sysmap.parrot.services.security.JwtService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	JwtService jwtService;

	@Autowired
	CommentService commentService;

	@Autowired
	LikeService likeService;

	@PostMapping("")
	@ApiOperation("Criar um novo post")
	public ResponseEntity<PostResponse> createNewPost(@RequestHeader("Authorization") String token, @Valid @RequestBody final PostRequest request) {

		request.verifyRequest();

		log.info("Criando um novo post");
		log.info("PostRequest {}", request);

		jwtService.isValidToken(token, request.getAuthorId());

		PostResponse response = postService.createPost(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	@ApiOperation("Buscar post pelo id")
	public ResponseEntity<PostResponse> getPostbyIdPost (@RequestHeader("Authorization") String token, @PathVariable UUID id) {

		log.info("Buscando post por id {} ", id) ;

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		PostResponse response = postService.getPostbyIdPost(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = "")
	@ApiOperation("Buscar os posts de quem eu sigo")
	public ResponseEntity<List<PostResponse>> getAllPostIFollow(@RequestHeader("Authorization") String token){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Iniciando a listagem dos posts");

		return ResponseEntity.ok().body(postService.getAllPostIFollow(idHeaders));
	}

	@GetMapping(params = "id")
	@ApiOperation("Buscar post pelo id do autor")
	public ResponseEntity<List<PostResponse>> getPostsByIdAuthor (@RequestHeader("Authorization") String token, @RequestParam UUID id) {

		log.info("Buscando post pelo id do autor {} ", id) ;

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		return ResponseEntity.ok().body(postService.getPostsByIdAuthor(id));
	}

	@PutMapping(value = "")
	@ApiOperation("Atualizando um post pelo id")
	public ResponseEntity<PostResponse> update(@RequestHeader("Authorization") String token, @RequestBody PostUpdateRequest request){

		jwtService.isValidToken(token, request.getIdAuthor());

		log.info("Atualizando um post pelo id");
		log.info("PostUpdateRequest request {} ", request);

		return ResponseEntity.ok().body(postService.updatePost(request));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePostByIdPost(@RequestHeader("Authorization") String token, @PathVariable UUID id){

		log.info("Deletando o usuario com o id{} ", id);

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);


		postService.deletePostByIdPost(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{idPost}/comment")
	@ApiOperation("Criar um comentario na postagem")
	public ResponseEntity<PostResponse> createComment(@RequestHeader("Authorization") String token,
													  @PathVariable UUID idPost,
													  @Valid @RequestBody final CommentRequest request) {

		log.info("Criando um novo comentário");
		log.info("CommentRequest {}", request);

		jwtService.isValidToken(token, request.getIdUser());

		PostResponse response = commentService.createComment(request, idPost);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@PutMapping(value = "/{idPost}/comment/{idComment}")
	@ApiOperation("Atualizando um comentário pelo id")
	public ResponseEntity<PostResponse> updateComment(@RequestHeader("Authorization") String token,
													  @PathVariable UUID idPost,
													  @PathVariable UUID idComment,
													  @RequestBody UpdateComentRequest request){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Atualizando um post pelo id");
		log.info("PostUpdateRequest request {} ", request);

		return ResponseEntity.ok().body(commentService.updateComment(request, idPost, idComment));
	}

	@DeleteMapping(value = "/{idPost}/comment/{idComment}")
	@ApiOperation("Deletar um comentário pelo id")
	public ResponseEntity<Void> deleteComment(@RequestHeader("Authorization") String token,
											  @PathVariable UUID idPost,
											  @PathVariable UUID idComment) {

		log.info("Deletando um comentario com o id{} " , idComment);

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		commentService.deleteComment(idPost, idComment);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("{idPost}/like")
	@ApiOperation("Dar like em uma postagem")
	public ResponseEntity<PostResponse> likePost (@RequestHeader("Authorization") String token,
													  @PathVariable UUID idPost,
													  @RequestBody @Valid LikeRequest request){

		jwtService.isValidToken(token, request.getIdUser());

		log.info("LikeRequest  {} ", request);

		return ResponseEntity.ok().body(likeService.likePost(request, idPost));
	}

	@PostMapping("{idPost}/comment/like")
	@ApiOperation("Dar like em um comentario")
	public ResponseEntity<PostResponse> likeComment (@RequestHeader("Authorization") String token,
													@PathVariable UUID idPost,
													@RequestBody @Valid LikeRequest request){

		jwtService.isValidToken(token, request.getIdUser());

		log.info("LikeRequest  {} ", request);

		return ResponseEntity.ok().body(likeService.likeComment(request, idPost));
	}

	@PostMapping("/{idPost}/unlike")
	@ApiOperation("Deslike de um post")
	public ResponseEntity<PostResponse> deslikePost (@RequestHeader("Authorization") String token,
													@PathVariable UUID idPost,
													@RequestBody @Valid LikeRequest request){

		jwtService.isValidToken(token, request.getIdUser());


		log.info("LikeRequest request  {} ", request);

		return ResponseEntity.ok().body(likeService.deslikePost(request, idPost));
	}

	@PostMapping("/{idPost}/comment/unlike")
	@ApiOperation("Deslike de um comentario")
	public ResponseEntity<PostResponse> deslikeComment (@RequestHeader("Authorization") String token,
													 	@PathVariable UUID idPost,
													 	@RequestBody @Valid LikeRequest request){

		jwtService.isValidToken(token, request.getIdUser());


		log.info("LikeRequest request  {} ", request);

		return ResponseEntity.ok().body(likeService.deslikeComment(request, idPost));
	}


}
