package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subbasoft.service.EmpService;
import com.subbasoft.service.EmpServiceImpl;

@RestController
public class HelloController {
	
	private EmpService empService;
	
	List<Employee> employees = new ArrayList<Employee>();


	@RequestMapping("/get/{empId}")
	public Employee getEmp(@PathVariable Long empId) {

		return empService.getEmployee(empId);
	}
	
	@RequestMapping("/all")
	public List<Employee> getAll() {

		
		return empService.getEmployees();
	}
	
	@PostMapping(path = "/emp/add", 
	        consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addEmployee(@RequestBody Employee emp) {
		empService.saveEmployee(emp);
	}
	
//	@DeleteMapping(path = "/emp/delete/{empId}")
//	public void delEmployee(@PathVariable int empId) {
//		for (Employee emp:employees) {
//			if (empId == emp.getEmpId()) {
//				employees.remove(emp);
//			}	
//		}
//	}
}