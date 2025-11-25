package com.algowebpro.security.auth.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

	private String accessToken;
	private String refreshToken;
	
	@Builder.Default
	private String tokenType = "Bearer";
	
	private Long userId;
	private String username;
	private String email;
	
	private Set<String> roles;

}
