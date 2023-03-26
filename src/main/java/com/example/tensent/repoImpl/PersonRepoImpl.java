package com.example.tensent.repoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.tensent.model.Person;
import com.example.tensent.repo.PersonRepo;

@Repository
public class PersonRepoImpl implements PersonRepo {

	private Connection con;
	PreparedStatement preparedStatement;
	Statement statement;
	
private static Logger logger = LoggerFactory.getLogger(PersonRepoImpl.class);
	
	@Autowired
	public PersonRepoImpl(Connection connection) {
		this.con = connection;
		createTable();
	} 
	
	private void createTable() {
		try {
			statement = con.createStatement();
			statement.execute("create table if not exists person(id int primary key auto_increment,first_name varchar(30),"
					+ "last_name varchar(30), age int , dob varchar(12))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void createPersonStatic(Person person) {
		
		try 
		{
			statement = con.createStatement();
			int result  = statement.executeUpdate("insert into person(first_name,last_name,age,dob) values('Peter','David',25,'04-12-1999')");
			logger.info("Insert Statement Result - {}",result);
			} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void createPerson(Person person) throws SQLException {
		this.preparedStatement = con.prepareStatement("insert into person(first_name,last_name, age, dob) values(?,?,?,?)");
		preparedStatement.setString(1, person.getFname());
		preparedStatement.setString(2, person.getLname());
		preparedStatement.setInt(3, person.getAge());
		preparedStatement.setString(4, person.getDob());
		
		int result = preparedStatement.executeUpdate();
		
		logger.info("Insert Statement Result - {}",result);
	}
	

	@Override
	public Person getPersonById(int id) {
		
		
		try {
			this.preparedStatement=con.prepareStatement("Select * from person where id=?");
			preparedStatement.setInt(1, id);
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next())
			{
				return getPersonFromRs(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Person getPersonFromRs(ResultSet rs) throws SQLException
	{		
		return Person.builder().id(rs.getInt(1)).fname(rs.getString(2)).lname(rs.getString(3)).dob(rs.getString(5)).age(rs.getInt(4)).build();
		
	}
	
	@Override
	public void updatePerson(int id) {
		
	}
	

	@Override
	public boolean deletePerson(int id) {
		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement("delete from person where id=?");
			ps.setInt(1, id);
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (result>0)?true:false;
	}

	@Override
	public List<Person> getAllPerson() {
		Statement st;
		List<Person> pl=new ArrayList<Person>();
		try {
			st = con.createStatement();
			ResultSet re =st.executeQuery("select * from Person");
			
			while(re.next())
			{
				Person p= getPersonFromRs(re);
				pl.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return pl;
	}

}
