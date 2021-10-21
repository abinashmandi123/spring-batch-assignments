package com.example.springbatchexample4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.springbatchexample4.batch.Processor;
import com.example.springbatchexample4.model.Employee;

public class ProcessorTest {

	@InjectMocks
	private Processor processor;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void processTest() throws Exception {
		
		Employee emp1=new Employee(101,"Steve","Technology",30000);
		Employee emp2=new Employee(102,"Rahul","Technology",15000);
		
		Assertions.assertNotNull(processor.process(emp1));
		
		Assertions.assertNull(processor.process(emp2));
	}
}
