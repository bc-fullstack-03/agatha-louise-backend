package com.sysmap.parrot.mappers.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

	UserResponse mappingToUserDTO(User user);

	List<UserResponse> mappingListUser (List<User> user);
}
