package com.algowebpro.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String department;
}
