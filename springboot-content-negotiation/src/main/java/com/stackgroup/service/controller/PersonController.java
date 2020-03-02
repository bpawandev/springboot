package com.stackgroup.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackgroup.service.document.Person;
import com.stackgroup.service.repository.PersonRepository;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
}
