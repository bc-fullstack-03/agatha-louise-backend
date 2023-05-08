package com.sysmap.parrot.services.security;

import java.util.UUID;

public interface JwtService {

	String generateToken(UUID id);
	boolean isValidToken(String token, UUID id);

	UUID getUserIdFromToken(String token);
}
