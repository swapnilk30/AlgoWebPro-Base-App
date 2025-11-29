package com.algowebpro.security.auth.service.impl;

import org.springframework.stereotype.Service;

import com.algowebpro.common.exception.BadRequestException;
import com.algowebpro.security.auth.dto.AuthResponse;
import com.algowebpro.security.auth.dto.LoginByEmailRequest;
import com.algowebpro.security.auth.dto.LoginRequest;
import com.algowebpro.security.auth.dto.RegisterRequest;
import com.algowebpro.security.auth.service.AuthService;
import com.algowebpro.ums.dto.UserDto;
import com.algowebpro.ums.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
//@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl {//implements AuthService {

	//private final UserService userService;
	//private final PasswordEncoder passwordEncoder;

/*	@Override
	public AuthResponse login(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthResponse register(RegisterRequest registerRequest) {
		log.info("Registration attempt for username: {}", registerRequest.getUsername());

		// Validate unique constraints
		validateRegistration(registerRequest);
		
		UserDto userDto = UserDto.builder()
				.username(registerRequest.getUsername())
				.email(registerRequest.getEmail())
				.password(passwordEncoder.encode(registerRequest.getPassword())).build();
		
		UserDto createdUser = userService.createUser(userDto);
		
		
		return null;
	}

	@Override
	public AuthResponse loginByEmail(LoginByEmailRequest loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	private void validateRegistration(RegisterRequest request) {
		if (userService.existsByUsername(request.getUsername())) {
			log.warn("Username already exists: {}", request.getUsername());
			throw new BadRequestException("Username is already taken!");
		}

		if (userService.existsByEmail(request.getEmail())) {
			log.warn("Email already exists: {}", request.getEmail());
			throw new BadRequestException("Email is already in use!");
		}
	}
	*/

}
