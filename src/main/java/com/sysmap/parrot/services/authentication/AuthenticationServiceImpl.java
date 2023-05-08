package com.sysmap.parrot.services.authentication;


import com.sysmap.parrot.entities.authentication.AuthenticateRequest;
import com.sysmap.parrot.entities.authentication.AuthenticateResponse;
import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.services.exceptions.DataIntegratyViolationException;
import com.sysmap.parrot.services.security.JwtService;
import com.sysmap.parrot.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;


	@Override
	public AuthenticateResponse authenticate(AuthenticateRequest request) {
		User user = userService.getUser(request.getEmail());


		if (!BCrypt.checkpw(request.getPassword(), user.getPassword())) {
			throw new DataIntegratyViolationException("Senha atual incorreta");
		}

		String token = jwtService.generateToken(user.getId());

		log.info("token jwt {} ", token);
		AuthenticateResponse response = new AuthenticateResponse(user.getId(), token);

		log.info("reponse  {} ", response);
		return response;
	}
}
