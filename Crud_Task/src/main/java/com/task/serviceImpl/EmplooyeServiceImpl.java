package com.task.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.task.dao.EmployeeRepo;
import com.task.entities.Employee;
import com.task.service.EmployeeService;
@Service
public class EmplooyeServiceImpl implements EmployeeService {
	
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public Employee insertEmployee(Employee employee)
	{
		
			Employee addEmp = employeeRepo.save(employee);
			if(addEmp !=null)
			{
			System.out.println("insert successfully : "+addEmp);
			}
		return addEmp;
	}

	@Cacheable(value = "employe")
	public List<Employee> getAllEmployee()
	{
		  List<Employee> allEmp = employeeRepo.findAll();
		  
		  return allEmp;
	}

	@CacheEvict(cacheNames = "employe", key = "#id", beforeInvocation = true)
	public void deleteEmployeeById(int id)
	{
		employeeRepo.deleteById(id);
		
	}

	@Cacheable(value = "employe", key="#id")
	public Optional<Employee> getEmployeeById(int id)
	{
		 return employeeRepo.findById(id);
		

	}
	@CachePut(cacheNames = "employe", key = "#id")
	public Employee updateEmployee(int id,Employee updatedEmployee)
	{
		Optional<Employee> optionalEmployee = employeeRepo.findById(id);
		if(!optionalEmployee.isPresent())
		{
			System.out.println("Employee not found with id " + id);
			throw new IllegalArgumentException("Employee not found with id " + id);
		}
		
		Employee existingEmployee = optionalEmployee.get();
		
		System.out.println("existing Employee : "+existingEmployee);
		
        existingEmployee.setFirstname(updatedEmployee.getFirstname());
        existingEmployee.setLastname(updatedEmployee.getLastname());
        existingEmployee.setMobileno(updatedEmployee.getMobileno());
        existingEmployee.setState(updatedEmployee.getState());
        existingEmployee.setCity(updatedEmployee.getCity());
		
        Employee updatedEmp = employeeRepo.save(existingEmployee);
        
        System.out.println("updated Emp :"+updatedEmp);
        
        return updatedEmp;
		
	}

}
