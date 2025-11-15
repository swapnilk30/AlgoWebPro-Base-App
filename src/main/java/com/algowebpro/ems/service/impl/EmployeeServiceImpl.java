package com.algowebpro.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.algowebpro.common.exception.ResourceNotFoundException;
import com.algowebpro.common.response.PageableResponse;
import com.algowebpro.common.utils.MappingUtil;
import com.algowebpro.ems.dto.EmployeeDto;
import com.algowebpro.ems.entity.Employee;
import com.algowebpro.ems.repository.EmployeeRepository;
import com.algowebpro.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	private final MappingUtil mappingUtil;

	@Override
	public List<EmployeeDto> getAllEmployees() {

		log.info("Fetching all employees from database");

		List<Employee> employees = employeeRepository.findAll();

		List<EmployeeDto> employeeDtos = mappingUtil.convertToDtoList(employees, EmployeeDto.class);

		log.debug("Total employees fetched: {}", employeeDtos.size());
		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {

		log.info("Fetching employee with ID: {}", id);

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		return mappingUtil.convertToDto(employee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		log.info("Saving new employee: {}", employeeDto.getFirstName());
		Employee employee = mappingUtil.convertToEntity(employeeDto, Employee.class);
		Employee savedEmployee = employeeRepository.save(employee);
		log.debug("Employee saved successfully with ID: {}", savedEmployee.getId());
		return mappingUtil.convertToDto(savedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmployee(Long id) {
		log.warn("Deleting employee with ID: {}", id);
		employeeRepository.deleteById(id);
		log.info("Employee deleted successfully");

	}

	@Override
	public PageableResponse<EmployeeDto> getAllEmployees(int page, int size, String sortBy, String sortDir) {

		log.info("Fetching employees with pagination: page={}, size={}, sortBy={}, sortDir={}", page, size, sortBy,
				sortDir);

		Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Employee> pageEmployee = employeeRepository.findAll(pageable);

		log.debug("Total employees fetched: {}, totalPages: {}", pageEmployee.getTotalElements(),
				pageEmployee.getTotalPages());

		List<EmployeeDto> dtoList = pageEmployee.getContent().stream()
				.map(emp -> mappingUtil.convertToDto(emp, EmployeeDto.class)).collect(Collectors.toList());

		PageableResponse<EmployeeDto> response = PageableResponse.<EmployeeDto>builder().content(dtoList)
				.pageNumber(pageEmployee.getNumber()).pageSize(pageEmployee.getSize())
				.totalElements(pageEmployee.getTotalElements()).totalPages(pageEmployee.getTotalPages())
				.isLast(pageEmployee.isLast()).build();

		return response;
	}

}
