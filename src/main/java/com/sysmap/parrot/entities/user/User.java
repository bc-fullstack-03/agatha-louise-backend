package com.sysmap.parrot.entities.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class User {

	private UUID idUser;
	private String nameUser;
	private String password;
	private String email;
	private List<UUID> follows;
	private List<UUID> following;

	public User(String nameUser, String password, String email, List<UUID> follows, List<UUID> following) {
		this.idUser = UUID.randomUUID();
		this.nameUser = nameUser;
		this.password = password;
		this.email = email;
		this.follows = follows;
		this.following = following;
	}
}
