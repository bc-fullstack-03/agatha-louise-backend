package com.sysmap.parrot.controllers.user;

import com.sysmap.parrot.entities.user.model.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.model.CreateUserRequest;
import com.sysmap.parrot.entities.user.model.UserRequest;
import com.sysmap.parrot.entities.user.model.UserResponse;
import com.sysmap.parrot.services.security.JwtService;
import com.sysmap.parrot.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	JwtService jwtService;

	@PostMapping("")
	@ApiOperation("Criar um novo User")
	public ResponseEntity<String> createUser (@RequestBody @Valid CreateUserRequest request){

		log.info("Criando um novo User");
		log.info("UserRequest {} ", request);

		String response = userService.createUser(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("Buscar usuário pelo id")
	public ResponseEntity<UserResponse> findById(@RequestHeader("Authorization") String token, @PathVariable UUID id){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Buscando user por id {} ", id);
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping(value = "")
	@ApiOperation("Buscar todos os usuarios")
	public ResponseEntity<List<UserResponse>> findAll(@RequestHeader("Authorization") String token){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Iniciando a listagem de todos os users");

		return ResponseEntity.ok().body(userService.findAll());
	}

	@PutMapping(value = "")
	public ResponseEntity<UserResponse> update(@RequestHeader("Authorization") String token, @RequestBody UserRequest request){

		jwtService.isValidToken(token, request.getId());

		log.info("Atualizando um user");
		log.info("UserResponse request {} ", request);

		return ResponseEntity.ok().body(userService.update(request));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@RequestHeader("Authorization") String token, @PathVariable UUID id){

		log.info("Deletando o usuario com o id{} ", id);

		jwtService.isValidToken(token, id);


		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/change/password")
	public ResponseEntity<Void> changePassword (@RequestHeader("Authorization") String token, @RequestBody ChangePasswordUserRequest request){

		log.info("Atualizando a senha ");
		log.info("UserResponse request {} ", request);

		jwtService.isValidToken(token, request.getId());

		userService.changePassword(request);

		return ResponseEntity.ok().build();
	}

	@GetMapping(params = "email")
	@ApiOperation("Buscar usuário pelo email")
	public ResponseEntity<UserResponse> findByemail(@RequestHeader("Authorization") String token, @RequestParam String email){

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		log.info("Buscando user por email {} ", email);
		return ResponseEntity.ok().body(userService.findByEmail(email));
	}

	@PostMapping("/photo/upload")
	public ResponseEntity uploadPhotoProfile(@RequestHeader("Authorization") String token, @RequestParam("photo") MultipartFile photo) {

		UUID idHeaders = jwtService.getUserIdFromToken(token);
		jwtService.isValidToken(token, idHeaders);

		try {

			userService.uploadPhotoProfile(photo, idHeaders );
			return new ResponseEntity(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

		}

	}

}
