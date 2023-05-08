package com.sysmap.parrot.entities.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ChangePasswordUserRequest {

	@NotBlank(message = "{user.id.not.null}")
	private UUID id;

	@NotBlank(message = "{user.currentPassword.not.blank}")
	@Size(min = 6, message = "{user.password.size.message}")
	private String currentPassword;

	@NotBlank(message = "{user.newPassword.not.blank}")
	@Size(min = 6, message = "{user.newPassword.size.message}")
	private String newPassword;

}
