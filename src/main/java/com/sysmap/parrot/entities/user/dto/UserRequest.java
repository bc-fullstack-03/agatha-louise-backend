package com.sysmap.parrot.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UserRequest {

	@NotBlank(message = "{user.id.not.null}")
	private UUID id;

	@Size(min = 2, max = 30, message = "{user.name.size.message}")
	private String name;

	@Email
	private String email;

}
