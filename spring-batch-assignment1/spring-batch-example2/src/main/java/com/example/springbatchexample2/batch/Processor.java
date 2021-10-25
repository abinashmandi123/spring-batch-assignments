package com.example.springbatchexample2.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatchexample2.model.User;

@Component
public class Processor implements ItemProcessor<User, User>{

   
    @Override
    public User  process(User user) throws Exception{
    	String str=user.getName();
    	char character=str.charAt(0);
    	if(character=='A') {
    		return null;
    	}
    	return user;
    }
}
