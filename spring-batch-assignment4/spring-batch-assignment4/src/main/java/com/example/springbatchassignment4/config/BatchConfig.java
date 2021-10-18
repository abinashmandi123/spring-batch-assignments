package com.example.springbatchassignment4.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.example.springbatchassignment4.model.User;

@Configuration
public class BatchConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserRowMapper rowMapper;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,ItemReader<User> itemReader,
			ItemProcessor<User,User> itemProcessor,ItemWriter<User> itemWriter) {
		
		Step step=stepBuilderFactory.get("batch-step").<User,User>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();
		
		return jobBuilderFactory.get("batch-job").start(step).incrementer(new RunIdIncrementer()).build();
	}
	
	@Bean 
	public JdbcCursorItemReader<User> itemReader() {
		JdbcCursorItemReader<User> reader=new JdbcCursorItemReader<>();
		
		reader.setDataSource(dataSource);
		reader.setSql("select ID, USERNAME, PASSWORD, AGE from USERS");
		reader.setRowMapper(rowMapper);
		
		return reader;
	}
	
	@Bean
	public StaxEventItemWriter<User> itemWriter(){
		
		StaxEventItemWriter<User> writer=new StaxEventItemWriter<>();
		
		writer.setResource(new FileSystemResource("src/main/resources/users.xml"));
		
		
		Map<String,String> map=new HashMap<>();
		map.put("user", "com.example.springbatchassignment4.model.User");
		
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(com.example.springbatchassignment4.model.User.class);
		
//		XStreamMarshaller marshaller=new XStreamMarshaller();
//		marshaller.setAliases(map);
		
		writer.setMarshaller(marshaller);
		writer.setRootTagName("users");
		
		return writer;
	}
}
