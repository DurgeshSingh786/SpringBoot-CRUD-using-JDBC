package com.example.tensent.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DBconfig {
	@Bean
	public Connection getCon()
	{
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl","root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
