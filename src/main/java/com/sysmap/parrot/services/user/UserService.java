package com.sysmap.parrot.services.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserRequest;
import com.sysmap.parrot.entities.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
	String createUser(CreateUserRequest request);

	UserResponse findById(UUID id);

	List<UserResponse> findAll();

	UserResponse update(UserRequest request);

	void delete(UUID id);

	void changePassword(ChangePasswordUserRequest resquest);

	UserResponse findByEmail(String email);

	User getUser(String email);
}
