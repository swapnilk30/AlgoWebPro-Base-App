package com.algowebpro.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

		Employee employee = employeeRepository.findById(id).orElseThrow(() -> {
			log.error("Employee not found with ID: {}", id);
			return new RuntimeException("Employee not found with id: " + id);
		});

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

}
