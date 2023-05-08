package com.sysmap.parrot.controllers.friend;

import com.sysmap.parrot.entities.friend.dto.FriendRequest;
import com.sysmap.parrot.entities.friend.dto.FriendResponse;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserResponse;
import com.sysmap.parrot.services.friend.FriendService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/friend")
public class FriendController {

	@Autowired
	FriendService service;

	@PostMapping("")
	@ApiOperation("Seguir um user")
	public ResponseEntity<FriendResponse> followUser (@RequestBody @Valid FriendRequest request){

		log.info("Follow request  {} ", request);

		return ResponseEntity.ok().body(service.followUser(request));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("Listar seguido/seguidores pelo id")
	public ResponseEntity<FriendResponse> findById(@PathVariable UUID id){

		log.info("Listando seguido/seguidores pelo id {} ", id);
		return ResponseEntity.ok().body(service.getListFollowerById(id));
	}

	@PostMapping("/unfollow")
	@ApiOperation("Deixar de seguir um user")
	public ResponseEntity<FriendResponse> unfollowUser (@RequestBody @Valid FriendRequest request){

		log.info("Unfollow request  {} ", request);

		return ResponseEntity.ok().body(service.unfollowUser(request));
	}

}
