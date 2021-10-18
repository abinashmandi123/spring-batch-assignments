package com.example.springbatchassignment10.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.springbatchassignment10.model.Student;

@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${student.rabbitmq.queue}")
	String queueName;
	
	@Value("${student.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${student.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(Student student) {
		rabbitTemplate.convertAndSend(exchange, routingkey, student);
		System.out.println("Send msg = " + student);
	    
	}
}
