package com.example.springbatchexample2.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbatchexample2.model.User;
import com.example.springbatchexample2.repository.UserRepository;

@Component
public class Writer implements ItemWriter<User>{
	 private UserRepository userRepository;

	    @Autowired
	    public Writer (UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public void write(List<? extends User> users) throws Exception{
	        System.out.println("Data Saved for Users: " + users);
	        userRepository.saveAll(users);
	    }

		
}
