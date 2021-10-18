package com.example.springbatchassignment3.config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.example.springbatchassignment3.model.Student;

public class StudentPreparedStatementSetter implements ItemPreparedStatementSetter<Student> {

	@Override
	public void setValues(Student student, PreparedStatement ps) throws SQLException {
		
		ps.setInt(1, student.getId());
		ps.setString(2,student.getName());
		ps.setInt(3, student.getMarks());

	}

}
