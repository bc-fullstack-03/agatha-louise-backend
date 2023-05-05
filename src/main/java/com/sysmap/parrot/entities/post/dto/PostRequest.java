package com.sysmap.parrot.entities.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
@ToString
public class PostRequest {

	private UUID id;
	private UUID idUser;
	private String nameUser;
	private Date date;
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
