package com.task.controller;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task.entities.Employee;
import com.task.service.EmployeeService;


@RestController
@RequestMapping("/task")
public class MainController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<Employee> insertEmp(@RequestBody Employee employee)
	{	
		System.out.println("employee :  "+employee);
		 try {
			Employee insertEmployee = employeeService.insertEmployee(employee);
			
			
			return new  ResponseEntity<>(insertEmployee, HttpStatus.CREATED);
			
		 } catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	}
	

	
	@GetMapping("/getall")
	public List<Employee> getAllEmp()
	{
		List<Employee> allEmployee = employeeService.getAllEmployee();
		System.out.println("allEmployee :::"+allEmployee);
		return allEmployee;
	}
	
	  	@DeleteMapping("/deleteEmp/{id}")
	    public void deleteEmployee(@PathVariable int id)
		{
		  
	  		employeeService.deleteEmployeeById(id);
		  
	  }
	  	
	  	@GetMapping("/getEmp/{id}")
		public ResponseEntity<Employee>  getEmpById(@PathVariable int id)
		{
	  			
	  		Optional<Employee> employee = employeeService.getEmployeeById(id);

	  		return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
			
		}
	  	
	  	
	  	@PutMapping("/updateEmp/{id}")
	  	public ResponseEntity<Employee> updateEmp(@PathVariable int id, @RequestBody Employee employee)
	  	{
	  	  try {
	            Employee updateEmployee = employeeService.updateEmployee(id, employee);
	            return ResponseEntity.ok(updateEmployee);
	        } catch (Exception e) {
	            return ResponseEntity.notFound().build();
	        }
	  	}
	  	
	  	
}
