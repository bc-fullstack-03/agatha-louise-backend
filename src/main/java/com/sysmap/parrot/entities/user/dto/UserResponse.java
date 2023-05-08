package com.sysmap.parrot.entities.user.dto;


import lombok.*;


import java.util.UUID;

@Data
@ToString
public class UserResponse {

	private UUID id;
	private String name;
	private String email;

}
