package com.sysmap.parrot.services.authentication;

import com.sysmap.parrot.entities.authentication.AuthenticateRequest;
import com.sysmap.parrot.entities.authentication.AuthenticateResponse;
import com.sysmap.parrot.entities.user.dto.ChangePasswordUserRequest;
import com.sysmap.parrot.entities.user.dto.CreateUserRequest;
import com.sysmap.parrot.entities.user.dto.UserRequest;
import com.sysmap.parrot.entities.user.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface AuthenticationService {

	AuthenticateResponse authenticate(AuthenticateRequest request);
}
