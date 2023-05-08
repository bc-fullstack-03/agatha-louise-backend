package com.sysmap.parrot.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
		setId();
		this.name = nameUser;
		this.password = password;
		this.email = email;
	}

	public void setId(){
		this.id = UUID.randomUUID();
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
	}

}
