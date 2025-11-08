package com.algowebpro.ems.service;

import java.util.List;

import com.algowebpro.ems.dto.EmployeeDto;

public interface EmployeeService {
	
	List<EmployeeDto> getAllEmployees();
	
	EmployeeDto getEmployeeById(Long id);
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
	
	void deleteEmployee(Long id);

}
