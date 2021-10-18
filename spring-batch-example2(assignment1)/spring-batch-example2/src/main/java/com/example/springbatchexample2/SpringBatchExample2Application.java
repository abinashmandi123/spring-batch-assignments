package com.example.springbatchexample2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBatchExample2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchExample2Application.class, args);
	}

}
