package com.algowebpro.ums.dto;

import java.util.UUID;

import com.algowebpro.ums.enums.RoleName;

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
public class RoleDto {
	
	private UUID id;
	private RoleName name;

}
