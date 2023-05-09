package com.sysmap.parrot.mappers.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.model.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper{

	@Override
	public UserResponse mappingToUserDTO(User user) {

		UserResponse userResponse = new UserResponse();
		userResponse.setId(user.getId());
		userResponse.setName(user.getName());
		userResponse.setEmail(user.getEmail());

		return userResponse;
	}

	@Override
	public List<UserResponse> mappingListUser(List<User> user) {
		return user.stream()
				.map(u -> {
					UserResponse userResponse = new UserResponse();
					userResponse.setId(u.getId());
					userResponse.setName(u.getName());
					userResponse.setEmail(u.getEmail());
					return userResponse;
				})
				.collect(Collectors.toList());
	}
}
