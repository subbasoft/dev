package com.subbasoft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Employee;

public interface EmpService {
	
	Employee saveEmployee(Employee emp);
	Employee getEmployee(Long empId);
	List<Employee> getEmployees();
	

}
