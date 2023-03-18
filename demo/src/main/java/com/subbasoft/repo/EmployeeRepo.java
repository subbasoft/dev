package com.subbasoft.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Employee;

public interface  EmployeeRepo extends JpaRepository<Employee, Long>{
	
	Employee findByEmpId(Long Id);

}
