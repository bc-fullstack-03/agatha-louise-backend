package com.sysmap.parrot.entities.post.model.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ToString
@Data
public class UpdateComentRequest {


	@Size(max = 200, message = "{comment.content.size.message}")
	@NotBlank(message = "{comment.not.null}")
	private String comment;


}
