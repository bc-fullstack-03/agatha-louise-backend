package com.sysmap.parrot.mappers.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper{

	@Override
	public UserDto mappingToUserDTO(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setFollows(user.getFollows());
		userDto.setFollowing(user.getFollowing());

		return userDto;
	}

	@Override
	public List<UserDto> mappingListUser(List<User> user) {
		return user.stream()
				.map(u -> {
					UserDto userDto = new UserDto();
					userDto.setId(u.getId());
					userDto.setName(u.getName());
					userDto.setEmail(u.getEmail());
					userDto.setFollows(u.getFollows());
					userDto.setFollowing(u.getFollowing());
					return userDto;
				})
				.collect(Collectors.toList());
	}
}
