package com.example.springbatchexample2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.springbatchexample2.model.User;

public interface UserRepository extends MongoRepository<User,Integer>{

}
