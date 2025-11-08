package com.algowebpro.ems.controller.view;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.algowebpro.ems.dto.EmployeeDto;
import com.algowebpro.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/employees/view")
@RequiredArgsConstructor
@Slf4j
public class EmployeeViewController {
	
	private final EmployeeService employeeService;
	
	@GetMapping
    public String listEmployees(Model model) {
		log.info("Fetching all employees for EMS view");
        
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        
        model.addAttribute("employees", employees);
        
        return "ems/employee-list";
    }

}
