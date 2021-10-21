package com.example.springbatchexample4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringBatchExample4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchExample4Application.class, args);
	}

}
