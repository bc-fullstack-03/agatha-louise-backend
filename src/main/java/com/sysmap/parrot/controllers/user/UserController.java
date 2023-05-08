package com.sysmap.parrot.controllers.user;

import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserRequest;
import com.sysmap.parrot.entities.user.dto.UserResponse;
import com.sysmap.parrot.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

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
	public ResponseEntity<UserResponse> findById(@PathVariable UUID id){

		log.info("Buscando user por id {} ", id);
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping(value = "")
	@ApiOperation("Buscar todos os usuarios")
	public ResponseEntity<List<UserResponse>> findAll(){

		log.info("Iniciando a listagem de todos os users");

		return ResponseEntity.ok().body(userService.findAll());
	}

	@PutMapping(value = "")
	public ResponseEntity<UserResponse> update(@RequestBody UserRequest request){

		log.info("Atualizando um user");
		log.info("UserResponse request {} ", request);

		return ResponseEntity.ok().body(userService.update(request));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserResponse> delete(@PathVariable UUID id){

		log.info("Deletando o usuario com o id{} ", id);

		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/change/password/{id}")
	public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody ChangePasswordUserRequest request){

		request.setId(id);

		log.info("Atualizando um user");
		log.info("UserResponse request {} ", request);

		userService.changePassword(request);

		return ResponseEntity.ok().build();
	}

	@GetMapping(params = "email")
	@ApiOperation("Buscar usuário pelo email")
	public ResponseEntity<UserResponse> findByemail(@RequestParam String email){

		log.info("Buscando user por email {} ", email);
		return ResponseEntity.ok().body(userService.findByEmail(email));
	}

}
