package com.algowebpro.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//@NotBlank(message = "First name is required")
	//@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
	//@Column(nullable = false)
	private String firstName;

	//@NotBlank(message = "Last name is required")
	//@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
	//@Column(nullable = false)
	private String lastName;

	//@Email(message = "Email should be valid")
	//@Column(unique = true, nullable = false)
	private String email;

	//@Pattern(regexp = "^\\+?[\\d\\s-]{10,}$", message = "Phone number should be valid")
	private String phoneNumber;

	//@NotBlank(message = "Department is required")
	private String department;
}
