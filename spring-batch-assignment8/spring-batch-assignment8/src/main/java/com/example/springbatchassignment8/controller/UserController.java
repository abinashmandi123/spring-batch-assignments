package com.example.springbatchassignment8.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbatchassignment8.model.User;
import com.example.springbatchassignment8.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/get/{id}")
	public  Optional<User> getValue(@PathVariable Integer id) {
		return userRepository.findById(id);
	}
	
	@GetMapping("/get")
	public List<User> getValue() {
		return userRepository.findAll();
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User user1=new User();
		user1.setId(user.getId());
		user1.setName(user.getName());
		user1.setDept(user.getDept());
		user1.setSalary(user.getSalary());
		
		userRepository.save(user1);
		
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
