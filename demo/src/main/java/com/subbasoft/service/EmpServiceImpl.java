package com.subbasoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Employee;
import com.subbasoft.repo.EmployeeRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service @RequiredArgsConstructor @Transactional
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	EmployeeRepo empRepo;


	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return empRepo.save(emp);
	}

	@Override
	public Employee getEmployee(Long empId) {
		// TODO Auto-generated method stub
		return empRepo.findByEmpId(empId);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

}
