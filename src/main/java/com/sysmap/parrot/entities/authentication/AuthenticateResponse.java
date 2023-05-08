package com.sysmap.parrot.entities.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticateResponse {

	private UUID id;
	private String token;

}
