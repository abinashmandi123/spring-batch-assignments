package com.example.springbatchassignment3.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchassignment3.model.Student;

@Component
public class Processor implements ItemProcessor<Student,Student>{

	@Override
	public Student process(Student item) throws Exception {
		// TODO Auto-generated method stub
		int marks=item.getMarks();
		if(marks<25) {
			return null;
		}
		return item;
	}

}
