package com.sysmap.parrot.services.authentication;

import com.sysmap.parrot.entities.authentication.AuthenticateRequest;
import com.sysmap.parrot.entities.authentication.AuthenticateResponse;

public interface AuthenticationService {

	AuthenticateResponse authenticate(AuthenticateRequest request);
}
