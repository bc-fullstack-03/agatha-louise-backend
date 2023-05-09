package com.sysmap.parrot.entities.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {

	@NotNull(message = "{user.id.not.null}")
	private UUID id;

	@Size(min = 2, max = 30, message = "{user.name.size.message}")
	private String name;

	@Email
	private String email;

}
