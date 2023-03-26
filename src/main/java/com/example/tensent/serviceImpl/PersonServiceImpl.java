package com.example.tensent.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tensent.BadPersonRequestException;
import com.example.tensent.controller.PersonController;
import com.example.tensent.dtos.createPersonDto;
import com.example.tensent.model.Person;
import com.example.tensent.repo.PersonRepo;
import com.example.tensent.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepo personRepos;
	
	public static Logger log=LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Override
	public void createPersonStatic(createPersonDto personDto) {
		Person person=personDto.to();
		
		
		if(person.getAge()==null)
		{
			person.setAge(calculateAgeFromDOB(person.getDob()));
			
		}
			
		
		try {
			personRepos.createPerson(person);
		} catch (SQLException e) {


			e.printStackTrace();
		}
		
		//personRepos.createPersonStatic(person);
		
		
	}
	
	private Integer calculateAgeFromDOB(String dob)
	{
		if(dob==null) {
			return null;
		}
		LocalDate dobDate=LocalDate.parse(dob);
		LocalDate currentDate=LocalDate.now();
		
		return Period.between(dobDate, currentDate).getYears();
	}

	@Override
	public Person getPersonById(int id) {
		
		return personRepos.getPersonById(id);
	}

	@Override
	public String deletePerson(int id) {
		Person person=personRepos.getPersonById(id);
		if(person == null) {
			return "User doesnot exist";
		}
		boolean isDeleted=personRepos.deletePerson(id);
		if(isDeleted) {
			return "Successfully Deleted";
		}
		return "NOT DEL : Error";
	}

	@Override
	public List<Person> getAllPerson() {
		
		return personRepos.getAllPerson();
	}

	

}
