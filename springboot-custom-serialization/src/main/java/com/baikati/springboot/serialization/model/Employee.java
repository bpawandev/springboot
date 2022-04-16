package com.baikati.springboot.serialization.model;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Employee {
	private int empNo;
	private String name;
	private String designation;
	private Instant instant = Instant.now();

	public Employee(int empNo, String name, String designation) {
		this.empNo = empNo;
		this.name = name;
		this.designation = designation;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public static List<Employee> getEmployees() {
		List<Employee> emps = Arrays.asList(new Employee(101, "Pawan", "Architect"),
				new Employee(102, "Preethi", "Executive HR"), new Employee(103, "Sunil", "Business Analyst"));
		return emps;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", name=" + name + ", designation=" + designation + ", instant=" + instant
				+ "]";
	}

}
