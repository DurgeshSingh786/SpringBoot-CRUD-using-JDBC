package com.example.tensent.service;

import java.util.List;

import com.example.tensent.dtos.createPersonDto;
import com.example.tensent.model.Person;

public interface PersonService {

	    void createPersonStatic(createPersonDto personDto);
	    Person getPersonById(int id);
		String  deletePerson(int id);
		List<Person> getAllPerson();
	
}


