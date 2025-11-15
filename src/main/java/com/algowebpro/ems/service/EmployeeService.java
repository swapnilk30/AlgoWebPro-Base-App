package com.algowebpro.ems.service;

import java.util.List;

import com.algowebpro.common.response.PageableResponse;
import com.algowebpro.ems.dto.EmployeeDto;

public interface EmployeeService {
	
	
	
	EmployeeDto getEmployeeById(Long id);
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);
	
	void deleteEmployee(Long id);
	
	List<EmployeeDto> getAllEmployees();
	
	PageableResponse<EmployeeDto> getAllEmployees(int page, int size, String sortBy, String sortDir);

}
