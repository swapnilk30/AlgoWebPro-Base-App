package com.algowebpro.ems.dto;

import com.algowebpro.common.validation.PhoneNumber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private Long id;
	
	@NotBlank(message = "First name is required")
    @Size(min = 2, message = "First name must be at least 2 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	private String lastName;
	
	@Email(message = "Invalid email format")
	private String email;
	
	@PhoneNumber
	private String phoneNumber;
	
	private String department;
}
