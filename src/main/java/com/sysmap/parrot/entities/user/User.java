package com.sysmap.parrot.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "users")
public class User {

	@Id
	private UUID id;
	private String name;
	private String password;
	@Indexed(unique = true)
	private String email;

	public User(String nameUser, String password, String email) {
		setIdUser();
		this.name = nameUser;
		this.password = password;
		this.email = email;
	}

	protected void setIdUser(){
		this.id = UUID.randomUUID();
	}
}
