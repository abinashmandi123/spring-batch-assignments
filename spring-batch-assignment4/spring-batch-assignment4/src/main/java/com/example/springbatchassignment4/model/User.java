package com.example.springbatchassignment4.model;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement(name = "user", namespace="")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int id;
	private String username;
	private String password;
	private int age;
}
