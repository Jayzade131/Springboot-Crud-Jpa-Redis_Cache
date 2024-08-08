package com.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
}
