package com.sysmap.parrot.entities.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Like {

	private UUID userId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Like like = (Like) o;

		return Objects.equals(userId, like.userId);
	}

	@Override
	public int hashCode() {
		return userId != null ? userId.hashCode() : 0;
	}
}
