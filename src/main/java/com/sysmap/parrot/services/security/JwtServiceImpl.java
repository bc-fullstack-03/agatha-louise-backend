package com.sysmap.parrot.services.security;

import com.sysmap.parrot.services.exceptions.UserNotAuthenticate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;


@Service
public class JwtServiceImpl implements JwtService {

	public static final long EXPIRATION_TIME = 7200000;
	public static final String KEY = "214125442A472D4B6150645367566B59703373367639792F423F4528482B4D62";

	public String generateToken(UUID userId) {
		return Jwts
				.builder()
				.setSubject(userId.toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(genSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isValidToken(String token, UUID id) {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(genSignInKey())
				.build()
				.parseClaimsJws(token.substring(7))
				.getBody();

		String sub = claims.getSubject();
		Date tExpiration = claims.getExpiration();

		if (!sub.equals(id.toString()) && !tExpiration.before(new Date())) {
			throw new UserNotAuthenticate("User not authenticate");
		}

		return true;
	}

	public UUID getUserIdFromToken(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(genSignInKey())
				.build()
				.parseClaimsJws(token.substring(7))
				.getBody();

		String userIdString = claims.getSubject();

		return UUID.fromString(userIdString);
	}



	private Key genSignInKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY));
	}
}
