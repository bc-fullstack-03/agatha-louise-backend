package com.sysmap.parrot.services.user;

import com.sysmap.parrot.data.UserRepository;
import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.model.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.model.CreateUserRequest;
import com.sysmap.parrot.entities.user.model.UserRequest;
import com.sysmap.parrot.entities.user.model.UserResponse;
import com.sysmap.parrot.mappers.user.UserMapper;
import com.sysmap.parrot.services.exceptions.DataIntegratyViolationException;
import com.sysmap.parrot.services.fileUpload.FileUploadService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

	public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Autowired
	FileUploadService fileUploadService;

	@Override
	public UserResponse findById(UUID id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(USUARIO_NAO_ENCONTRADO));

		return mapper.mappingToUserDTO(user);
	}

	@Override
	public List<UserResponse> findAll() {
		return mapper.mappingListUser(repository.findAll());
	}

	@Override
	public String createUser(CreateUserRequest request) {

		if(repository.findByEmail(request.getEmail()).isPresent()){
			throw new DataIntegratyViolationException("Email já cadastrado");
		}

		User user = new User();
		user.setId();
		user.setPassword(request.getPassword());
		user.setName(request.getName());
		user.setEmail(request.getEmail());

		repository.save(user);

		return user.getId().toString();
	}

	@Override
	public UserResponse update(UserRequest request) {

		Optional<User> userOptional = repository.findById(request.getId());

		if (userOptional.isEmpty()) {
			throw new DataIntegratyViolationException(USUARIO_NAO_ENCONTRADO);
		}

		if(repository.findByEmail(request.getEmail()).isPresent()){
			throw new DataIntegratyViolationException("Email já cadastrado");
		}


		User user = userOptional.get();

		if(request.getName() != null) {
			user.setName(request.getName());
		}

		if (request.getEmail() != null) {
			user.setEmail(request.getEmail());
		}

		return mapper.mappingToUserDTO(repository.save(user));
	}

	@Override
	public void delete(UUID id) {
		findById(id);
		repository.deleteById(id);
	}


	public void changePassword(ChangePasswordUserRequest request) {
		Optional<User> userOptional = repository.findById(request.getId());

		if (userOptional.isEmpty()) {
			throw new DataIntegratyViolationException(USUARIO_NAO_ENCONTRADO);
		}

		User user = userOptional.get();

		if (!BCrypt.checkpw(request.getCurrentPassword(), user.getPassword())) {
			throw new DataIntegratyViolationException("Senha atual incorreta");
		}

		user.setPassword(request.getNewPassword());
		repository.save(user);
	}

	@Override
	public UserResponse findByEmail(String email) {
		User user = repository.findByEmail(email)
				.orElseThrow(() -> new NoSuchElementException(USUARIO_NAO_ENCONTRADO));

		return mapper.mappingToUserDTO(user);
	}

	@Override
	public User getUser(String email) {
		return repository.findByEmail(email)
				.orElseThrow(() -> new NoSuchElementException(USUARIO_NAO_ENCONTRADO));

	}

	@Override
	public void uploadPhotoProfile(MultipartFile photo, UUID idUser) throws Exception {

		User user = repository.findById(idUser)
				.orElseThrow(() -> new NoSuchElementException(USUARIO_NAO_ENCONTRADO));

		var photoUri = "";

		try {
			var fileName = idUser + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
			photoUri = fileUploadService.upload(photo, fileName);

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

		user.setPhotoUri(photoUri);
		repository.save(user);
	}
}
