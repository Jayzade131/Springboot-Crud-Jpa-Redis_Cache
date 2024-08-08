package com.task.service;

import java.util.List;
import java.util.Optional;

import com.task.entities.Employee;

public interface EmployeeService {
	public Employee insertEmployee(Employee employee);
	
	public List<Employee> getAllEmployee();
	
	public void deleteEmployeeById(int id);
	
	public Optional<Employee> getEmployeeById(int id);
	
	public Employee updateEmployee(int id,Employee updatedEmployee);
}
