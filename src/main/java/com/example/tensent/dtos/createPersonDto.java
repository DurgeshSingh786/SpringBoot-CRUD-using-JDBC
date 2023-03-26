package com.example.tensent.dtos;




import org.springframework.stereotype.Component;

import com.example.tensent.model.Person;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Builder;

@Data
public class createPersonDto {

	@NotBlank(message = "First Name Should Not be Empty")
	private String fname;
	private String lname;
	private String dob;
	
	public Person to()
	{
		
		return Person.builder().fname(fname).lname(lname).dob(dob).build();
	}

}
