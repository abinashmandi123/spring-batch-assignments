package com.example.springbatchassignment10.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchassignment10.model.Student;

@Component
public class Processor implements ItemProcessor<Student,Student>{

	@Override
	public Student process(Student item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
