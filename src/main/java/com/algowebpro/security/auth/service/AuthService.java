package com.algowebpro.security.auth.service;

import com.algowebpro.security.auth.dto.AuthResponse;
import com.algowebpro.security.auth.dto.LoginByEmailRequest;
import com.algowebpro.security.auth.dto.LoginRequest;
import com.algowebpro.security.auth.dto.RegisterRequest;

public interface AuthService {

	AuthResponse login(LoginRequest loginRequest);

	AuthResponse register(RegisterRequest registerRequest);

	AuthResponse loginByEmail(LoginByEmailRequest loginRequest);

}
