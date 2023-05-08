package com.sysmap.parrot.controllers.authentication;

import com.sysmap.parrot.entities.authentication.AuthenticateRequest;
import com.sysmap.parrot.entities.authentication.AuthenticateResponse;
import com.sysmap.parrot.services.authentication.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@PostMapping()
	@ApiOperation("Autenticar um usuario")
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest request){

		return ResponseEntity.ok().body(service.authenticate(request));
	}
}
