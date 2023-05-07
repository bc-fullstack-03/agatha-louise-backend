package com.sysmap.parrot.controllers.user;

import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserDto;
import com.sysmap.parrot.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.swagger2.mappers.ModelMapper;
import springfox.documentation.swagger2.mappers.ModelMapperImpl;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
	public ResponseEntity<UserDto> findById(@PathVariable UUID id){

		log.info("Buscando user por id {} ", id);
		return ResponseEntity.ok().body(userService.findById(id));
	}

	@GetMapping(value = "")
	@ApiOperation("Buscar todos os usuarios")
	public ResponseEntity<List<UserDto>> findAll(){

		log.info("Iniciando a listagem de todos os users");

		return ResponseEntity.ok()
				.body(userService.findAll());
	}


	@PutMapping(value = "")
	public ResponseEntity<UserDto> update(@RequestBody UserDto request){

		log.info("Atualizando um user");
		log.info("UserDto request {} ", request);

		return ResponseEntity.ok().body(userService.update(request));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<UserDto> delete(@PathVariable UUID id){

		log.info("Deletando o usuario com o id{} ", id);

		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/change/password/{id}")
	public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody ChangePasswordUserRequest request){

		request.setId(id);

		log.info("Atualizando um user");
		log.info("UserDto request {} ", request);

		userService.changePassword(request);

		return ResponseEntity.ok().build();
	}
}
