package com.baikati.springboot.serialization.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baikati.springboot.serialization.model.Employee;

@RestController
public class EmployeeController {
	@RequestMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return Employee.getEmployees();
    }
}	
