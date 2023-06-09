package com.sysmap.parrot.controllers.friend;

import com.sysmap.parrot.entities.friend.model.FriendRequest;
import com.sysmap.parrot.entities.friend.model.FriendResponse;
import com.sysmap.parrot.services.friend.FriendService;
import com.sysmap.parrot.services.security.JwtService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/friend")
public class FriendController {

	@Autowired
	FriendService service;

	@Autowired
	JwtService jwtService;


	@PostMapping("")
	@ApiOperation("Seguir um user")
	public ResponseEntity<FriendResponse> followUser (@RequestHeader("Authorization") String token, @RequestBody @Valid FriendRequest request){

		jwtService.isValidToken(token, request.getFollowerId());

		log.info("Follow request  {} ", request);

		return ResponseEntity.ok().body(service.followUser(request));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("Listar seguido/seguidores pelo id")
	public ResponseEntity<FriendResponse> findById(@RequestHeader("Authorization") String token, @PathVariable UUID id){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Listando seguido/seguidores pelo id {} ", id);
		return ResponseEntity.ok().body(service.getListFollowerById(id));
	}

	@PostMapping("/unfollow")
	@ApiOperation("Deixar de seguir um user")
	public ResponseEntity<FriendResponse> unfollowUser (@RequestHeader("Authorization") String token, @RequestBody @Valid FriendRequest request){

		jwtService.isValidToken(token, request.getFollowerId());


		log.info("Unfollow request  {} ", request);

		return ResponseEntity.ok().body(service.unfollowUser(request));
	}

}
