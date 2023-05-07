package com.sysmap.parrot.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {

	@Id
	private UUID id;

	@Size(min = 2, max = 30, message = "{user.name.size.message}")
	private String name;

	@Email
	@Indexed(unique = true)
	private String email;

}
