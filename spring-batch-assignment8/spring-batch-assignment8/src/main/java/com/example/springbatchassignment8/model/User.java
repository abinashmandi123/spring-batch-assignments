package com.example.springbatchassignment8.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class User {

	private Integer id;
	private String name;
	private String dept;
	private Integer salary;
}
