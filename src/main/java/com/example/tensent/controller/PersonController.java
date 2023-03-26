package com.example.tensent.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tensent.dtos.createPersonDto;
import com.example.tensent.model.Person;
import com.example.tensent.service.PersonService;

import jakarta.validation.Valid;

@RestController
public class PersonController 
{

	@Autowired
	PersonService personf;
	
	public static Logger log=LoggerFactory.getLogger(PersonController.class);
	
	
	@GetMapping("/getperson")			//GET PERSON BY ID
	public Person getPersonById(@RequestParam("id") int id) 
	{
		return personf.getPersonById(id);
		
	}
	
	@GetMapping("/persons")
	public List<Person> getPersons()
	{
		return personf.getAllPerson();
	}
	
	@DeleteMapping("/delete")
	public String delPersonById(@RequestParam("id") int id)
	{
		return personf.deletePerson(id);
	}
	
	
	@PostMapping("/person")			// CREATING PERSON
	public ResponseEntity createPerson(@RequestBody @Valid createPersonDto personDto)    //(@RequestBody Person person) sending object will expose us, NOT RECOMMENDED, use DTO(data transfer Objects)
	{
//		if(person.getfname()==null || person.getfname().isEmpty())    // NOT recommded to put Validation BUSINESS Logic in Controller
//		{															 //  instead we can use spring-starter-validation.
//			log.error("invalid fname");
//			return new ResponseEntity(HttpStatus.BAD_REQUEST);
//		}
		personf.createPersonStatic(personDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	
	
	

}
