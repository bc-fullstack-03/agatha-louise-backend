package com.sysmap.parrot.entities.post.model.like;

import jakarta.validation.constraints.NotNull;
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
public class LikeRequest {

	//opcional
	private UUID idComment;

	@NotNull(message = "{user.id.not.null}")
	private  UUID idUser;

}
