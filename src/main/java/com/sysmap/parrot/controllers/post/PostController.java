package com.sysmap.parrot.controllers.post;

import com.sysmap.parrot.entities.post.dto.PostRequest;
import com.sysmap.parrot.services.post.PostService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/post")
	@ApiOperation("Criar um novo post")
	public ResponseEntity<PostResponse> createNewPost(@Valid @RequestBody final PostRequest request) {

		log.info("Criando um novo post");
		log.info("PostRequest {}", request);

		//TODO fazer autenticação
		//Todo ver o date

		PostResponse = postService.createPost(request);

		response -> log.info("Post criado com os dados: {}", response);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
