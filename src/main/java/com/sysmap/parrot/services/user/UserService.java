package com.sysmap.parrot.services.user;

import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.model.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.model.CreateUserRequest;
import com.sysmap.parrot.entities.user.model.UserRequest;
import com.sysmap.parrot.entities.user.model.UserResponse;
import org.springframework.web.multipart.MultipartFile;

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

	void uploadPhotoProfile(MultipartFile photo, UUID idUser) throws Exception;
}
