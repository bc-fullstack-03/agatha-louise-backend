package com.sysmap.parrot.entities.post.model.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
@ToString
public class CommentRequest {

	@NotNull(message = "{user.id.not.null}")
	private  UUID idUser;

	@Size(max = 200, message = "{comment.content.size.message}")
	@NotBlank(message = "{comment.not.null}")
	private String comment;

}
