package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	Employee emp1 = new Employee(1,"Subba",20000);
	Employee emp2 = new Employee(2,"Sowji",12000);
	Employee emp3 = new Employee(3,"Vivek",9000);
	Employee emp4 = new Employee(4,"Sai",5000);
	Employee emp5 = new Employee(5,"Suseela",15000);
	
	List<Employee> employees = new ArrayList<Employee>();


	@RequestMapping("/get/{empId}")
	public Employee getEmp(@PathVariable int empId) {
		for (Employee emp:employees) {
			if (empId == emp.getEmpId()) {
				return emp;
			}	
		}
		return null;
	}
	
	@RequestMapping("/all")
	public List<Employee> getAll() {
		employees = new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		employees.add(emp5);
		
		return employees;
	}
	
	@PostMapping(path = "/emp/add", 
	        consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addEmployee(@RequestBody Employee emp) {
		employees.add(emp);
	}
	
	@DeleteMapping(path = "/emp/delete/{empId}")
	public void delEmployee(@PathVariable int empId) {
		for (Employee emp:employees) {
			if (empId == emp.getEmpId()) {
				employees.remove(emp);
			}	
		}
	}
}