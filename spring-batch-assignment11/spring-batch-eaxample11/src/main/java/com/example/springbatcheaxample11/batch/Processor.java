package com.example.springbatcheaxample11.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatcheaxample11.model.Student;

@Component
public class Processor implements ItemProcessor<String,String>{

	@Override
	public String process(String item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

	

	

	
}
