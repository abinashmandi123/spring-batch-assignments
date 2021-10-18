package com.example.springbatchexample4.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchexample4.model.Employee;

@Component
public class Processor implements ItemProcessor<Employee,Employee>{

	@Override
	public Employee process(Employee item) throws Exception {
		// TODO Auto-generated method stub
		int salary=item.getSalary();
		if(salary<20000) {
			return null;
		}
		return item;
	}

}
