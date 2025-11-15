package com.algowebpro.ems.controller.api.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algowebpro.common.response.ApiResponse;
import com.algowebpro.common.response.PageableResponse;
import com.algowebpro.ems.dto.EmployeeDto;
import com.algowebpro.ems.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeRestController {

	private final EmployeeService employeeService;

	// -------------------------
	// 1. GET ALL (NO PAGINATION)
	// -------------------------
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
		log.info("Received request: GET /api/employees/all");
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}

	// -------------------------
	// 2. PAGINATION + SORTING
	// -------------------------
	@GetMapping
	public ResponseEntity<ApiResponse<PageableResponse<EmployeeDto>>> getAllEmployees(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String sortDir) {

		log.info("API Request: GET /api/v1/employees?page={}&size={}&sortBy={}&sortDir={}", page, size, sortBy,
				sortDir);
		PageableResponse<EmployeeDto> response = employeeService.getAllEmployees(page, size, sortBy, sortDir);

		log.info("Employees fetched successfully. Returning {} records.", response.getContent().size());

		return ResponseEntity.ok(ApiResponse.<PageableResponse<EmployeeDto>>builder().success(true)
				.message("Employees fetched successfully").data(response).build());
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
	public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		log.info("Received request: POST /api/employees -> {}", employeeDto.getFirstName());

		EmployeeDto created = employeeService.createEmployee(employeeDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<EmployeeDto>builder().success(true)
				.message("Employee created successfully").data(created).build());
	}
}
