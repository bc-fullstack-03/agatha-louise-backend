package com.sysmap.parrot.services.user;

import com.sysmap.parrot.data.UserRepository;
import com.sysmap.parrot.entities.user.User;
import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserResponse;
import com.sysmap.parrot.mappers.user.UserMapper;
import com.sysmap.parrot.services.exceptions.DataIntegratyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

	public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

	@Autowired
	UserRepository repository;

	@Autowired
	UserMapper mapper;

	@Override
	public UserResponse findById(UUID id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(USUARIO_NAO_ENCONTRADO));

		return mapper.mappingToUserDTO(user);
	}

	@Override
	public List<UserResponse> findAll(){
		return mapper.mappingListUser(repository.findAll());
	}

	@Override
	public String createUser(CreateUserRequest request){
		findByEmail(request.getEmail());

		User user = new User(request.getName(), request.getPassword(), request.getEmail());
		repository.save(user);

		return user.getId().toString();
	}

	@Override
	public UserResponse update(UserResponse request) {

		Optional<User> userOptional = repository.findById(request.getId());
		this.findByEmail(request.getEmail());

		if (userOptional.isEmpty()) {
			throw new DataIntegratyViolationException(USUARIO_NAO_ENCONTRADO);
		}

		User user = userOptional.get();
		user.setName(request.getName());
		user.setEmail(request.getEmail());

		return mapper.mappingToUserDTO(repository.save(user));
	}

	@Override
	public void delete(UUID id) {
		findById(id);
		repository.deleteById(id);
	}


	public void changePassword(ChangePasswordUserRequest resquest) {
		Optional<User> userOptional = repository.findById(resquest.getId());

		if (userOptional.isEmpty()) {
			throw new DataIntegratyViolationException(USUARIO_NAO_ENCONTRADO);
		}

		User user = userOptional.get();
		if (!user.getPassword().equals(resquest.getCurrentPassword())) {
			throw new DataIntegratyViolationException("Senha atual incorreta");
		}

		user.setPassword(resquest.getNewPassword());
		repository.save(user);
	}

	private void findByEmail(String email){
		Optional<User> user = repository.findByEmail(email);
		if(user.isPresent() && user.get().getEmail().equals(email)){
			throw new DataIntegratyViolationException("Email já cadastrado no sistema");
		}
	}
}
