package com.example.springbatchexample4.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.springbatchexample4.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Integer>{

}
