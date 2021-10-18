package com.example.springbatchexample2.model;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private Integer id;
	private String name;
	private String dept;
	private Integer salary;
	private Date time;
}
