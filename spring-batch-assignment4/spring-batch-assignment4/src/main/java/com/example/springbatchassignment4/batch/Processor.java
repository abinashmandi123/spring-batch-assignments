package com.example.springbatchassignment4.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchassignment4.model.User;

@Component
public class Processor implements ItemProcessor<User,User> {

	@Override
	public User process(User item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
