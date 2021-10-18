package com.example.springbatchassignment8.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springbatchassignment8.model.User;

@Repository
public  interface UserRepository extends MongoRepository<User,Integer>{

}
