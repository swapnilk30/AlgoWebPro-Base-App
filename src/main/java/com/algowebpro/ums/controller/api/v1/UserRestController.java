package com.algowebpro.ums.controller.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algowebpro.common.response.ApiResponse;
import com.algowebpro.ums.dto.UserDto;
import com.algowebpro.ums.service.UserService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {
	
	private final UserService userService;
	
	
	@PostMapping
	public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {
		log.info("Received request: POST /api/v1/users -> {}", userDto.getUsername());

		UserDto created = userService.createUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<UserDto>builder().success(true)
				.message("User created successfully").data(created).build());
	}
	
	
	
	
	
}
