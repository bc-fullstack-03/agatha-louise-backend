package com.sysmap.parrot.entities.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotBlank(message = "{user.name.not.blank}")
	@Size(min = 2, max = 30, message = "{user.name.size.message}")
	private String userName;

	@NotBlank(message = "{user.password.not.blank}")
	@Size(min = 6, message = "{user.password.size.message}")
	private String password;

	@Email
	@NotBlank(message = "{user.email.not.blank}")
	private String email;

}
