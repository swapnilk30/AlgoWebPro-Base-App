package com.algowebpro.ums.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.algowebpro.ums.enums.Provider;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private UUID id;

	private String email;

	private String username;

	private String password;

	private boolean enabled = true;

	private Provider provider = Provider.LOCAL;

	private Set<RoleDto> roles = new HashSet<>();
}
