package com.algowebpro.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algowebpro.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
