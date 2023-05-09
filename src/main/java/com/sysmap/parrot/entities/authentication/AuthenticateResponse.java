package com.sysmap.parrot.entities.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AuthenticateResponse {

	private UUID id;
	private String token;

}
