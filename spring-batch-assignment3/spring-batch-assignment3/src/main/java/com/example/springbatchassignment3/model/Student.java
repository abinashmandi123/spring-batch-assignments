package com.example.springbatchassignment3.model;


import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@XmlRootElement(name = "student", namespace="")
//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	private Integer id;
	private String name;
	private Integer marks;
}
