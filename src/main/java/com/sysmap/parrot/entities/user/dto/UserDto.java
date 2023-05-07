package com.sysmap.parrot.entities.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {

	@Id
	@NotNull(message = "{user.id.not.null}")
	private UUID id;

	@Size(min = 2, max = 30, message = "{user.name.size.message}")
	private String name;

	@Email
	@Indexed(unique = true)
	private String email;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<UUID> follows;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private List<UUID> following;

}
