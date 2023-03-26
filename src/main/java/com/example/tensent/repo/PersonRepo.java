package com.example.tensent.repo;

import java.sql.SQLException;
import java.util.List;

import com.example.tensent.model.Person;

public interface PersonRepo {

	void createPersonStatic(Person person);
	void createPerson(Person person) throws SQLException;
	Person getPersonById(int id);
	void updatePerson(int id);
	boolean deletePerson(int id);
	List<Person> getAllPerson();
}
