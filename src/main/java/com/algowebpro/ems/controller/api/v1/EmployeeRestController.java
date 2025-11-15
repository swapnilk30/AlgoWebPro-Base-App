package com.algowebpro.ems.controller.api.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algowebpro.common.response.ApiResponse;
import com.algowebpro.ems.dto.EmployeeDto;
import com.algowebpro.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
//@Slf4j
public class EmployeeRestController {

	private static final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);

	private final EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		log.info("Received request: GET /api/employees");
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
		log.info("Received request: GET /api/employees/{}", id);
		
		EmployeeDto employee = employeeService.getEmployeeById(id);

		ApiResponse<EmployeeDto> response = ApiResponse.<EmployeeDto>builder().success(true)
				.message("Employee fetched successfully").data(employee).build();

		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		log.info("Received request: POST /api/employees -> {}", employeeDto.getFirstName());
		return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
	}
}
