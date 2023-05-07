package com.sysmap.parrot.mappers.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {

	UserDto mappingToUserDTO(User user);

	List<UserDto> mappingListUser (List<User> user);
}
