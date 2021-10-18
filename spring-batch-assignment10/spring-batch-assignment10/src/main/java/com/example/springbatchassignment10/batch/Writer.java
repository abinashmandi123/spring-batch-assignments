package com.example.springbatchassignment10.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbatchassignment10.model.Student;
import com.example.springbatchassignment10.service.RabbitMQSender;

@Component
public class Writer implements ItemWriter<Student>{
	
	@Autowired
	private RabbitMQSender rabbitMqSender;

	@Override
	public void write(List<? extends Student> items) throws Exception {
		// TODO Auto-generated method stub
		for(Student item:items) {
			rabbitMqSender.send(item);
		
	}

	}
}
