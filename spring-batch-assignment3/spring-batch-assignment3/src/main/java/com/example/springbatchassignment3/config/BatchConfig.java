package com.example.springbatchassignment3.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.example.springbatchassignment3.model.Student;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,ItemReader<Student> itemReader,
			ItemProcessor<Student,Student> itemProcessor,ItemWriter<Student> itemWriter) {
		
		Step step=stepBuilderFactory.get("batch-step").<Student,Student>chunk(100).reader(itemReader)
				.processor(itemProcessor).writer(itemWriter).build();
		
		return jobBuilderFactory.get("batch-job").start(step).incrementer(new RunIdIncrementer()).build();
	}
	
	@Bean
	public StaxEventItemReader<Student> itemReader() {
		StaxEventItemReader<Student> reader=new StaxEventItemReader<>();
		reader.setResource(new FileSystemResource("src/main/resources/student.xml"));
		reader.setFragmentRootElementName("student");
		
		
		Map<String,String> map=new HashMap<>();
		map.put("student","com.example.springbatchassignment3.model.Student");
		
		
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(com.example.springbatchassignment3.model.Student.class);
		
//		XStreamMarshaller marshaller = new XStreamMarshaller();
//		marshaller.setAliases(map);
		
		reader.setUnmarshaller(marshaller);
		return reader;

	}
	
	@Bean
	public JdbcBatchItemWriter<Student> writer(){
		JdbcBatchItemWriter<Student> writer = new JdbcBatchItemWriter<Student>();
		writer.setDataSource(dataSource);
		writer.setSql("INSERT INTO student(id,name,marks) VALUES(?,?,?)");
		writer.setItemPreparedStatementSetter(new StudentPreparedStatementSetter());
		return writer;
	}
	
	
}
