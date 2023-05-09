package com.sysmap.parrot.entities.post.model;

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
public class PostRequest {

	@NotNull(message = "{user.id.not.null}")
	private UUID authorId;

	@Size(max = 300, message = "{post.content.size.message}")
	private String content;
	private String media;


	public void verifyRequest() {

		log.info("verificando PostRequest:");
		log.info("content: {}", content);
		log.info("media: {}", media);


		if (content == null && media == null) {
			log.info("=============> Request nao valido");
			throw new IllegalArgumentException("Post sem conteúdo");
		}

		if (content != null && media != null) {
			if (content.isEmpty() && media.isEmpty()) {
				log.info("=============> Request nao valido");
				throw new IllegalArgumentException("Post sem conteúdo");
			}
		}

	}

}
