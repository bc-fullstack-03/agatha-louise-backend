package com.sysmap.parrot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class ParrotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParrotApplication.class, args);

		log.info(">>>> Bem vindo ao parrot! <<<<<");
	}

}
