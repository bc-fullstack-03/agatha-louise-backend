package com.sysmap.parrot.services.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
	String createUser(CreateUserRequest request);
	UserDto findById(UUID id);
	List<UserDto> findAll();
	UserDto update(UserDto request);
	void delete(UUID id);
	void changePassword(ChangePasswordUserRequest resquest);



}
