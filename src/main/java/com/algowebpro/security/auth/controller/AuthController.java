package com.algowebpro.security.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algowebpro.common.response.ApiResponse;
import com.algowebpro.security.auth.dto.AuthResponse;
import com.algowebpro.security.auth.dto.LoginByEmailRequest;
import com.algowebpro.security.auth.dto.LoginRequest;
import com.algowebpro.security.auth.dto.RegisterRequest;
import com.algowebpro.security.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final AuthService authService;

	/**
	 * Login endpoint POST /api/v1/auth/login
	 */
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {

		log.info("POST /api/auth/login - Login request for: {}", loginRequest.getUsernameOrEmail());

		AuthResponse response = authService.login(loginRequest);

		// log.info("Login successful for user: {}", response.getUsername());

		// return ResponseEntity.ok(ApiResponse.success("Login successful", response));
		return null;
	}

	/**
	 * Login by email endpoint (email only) POST /api/v1/auth/login/email
	 */
	@PostMapping("/login/email")
	public ResponseEntity<ApiResponse<AuthResponse>> loginByEmail(
			@Valid @RequestBody LoginByEmailRequest loginRequest) {
		log.info("POST /api/auth/login/email - Login request for email: {}", loginRequest.getEmail());

		AuthResponse response = authService.loginByEmail(loginRequest);

		//log.info("Login by email successful for user: {}", response.getUsername());
		//return ResponseEntity.ok(ApiResponse.success("Login successful", response));
		return null;
	}

	/**
	 * Register endpoint POST /api/v1/auth/register
	 */
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
		log.info("POST /api/auth/register - Registration request for: {}", registerRequest.getUsername());

		AuthResponse response = authService.register(registerRequest);

		// log.info("Registration successful for user: {}", response.getUsername());
		// return
		// ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Registration
		// successful", response));
		return null;
	}

}
