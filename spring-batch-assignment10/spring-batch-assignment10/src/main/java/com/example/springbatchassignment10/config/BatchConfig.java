package com.example.springbatchassignment10.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.springbatchassignment10.model.Student;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			ItemReader<Student> itemReader,ItemProcessor<Student,Student> itemProcessor,ItemWriter<Student> itemWriter) {
		
		
		return jobBuilderFactory.get("batch-job").incrementer(new RunIdIncrementer()).start(step(itemReader,itemProcessor,itemWriter)).build();
	}
	
	@Bean
	public Step step(ItemReader<Student> itemReader,ItemProcessor<Student,Student> itemProcessor,ItemWriter<Student> itemWriter) {
		
		return stepBuilderFactory.get("batch-step").<Student,Student>chunk(100).reader(itemReader).processor(itemProcessor)
				.writer(itemWriter).build();
	}
	
	@Bean
	public FlatFileItemReader<Student> itemReader(){
		
		   FlatFileItemReader<Student> flatFileItemReader = new FlatFileItemReader<>();
	        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/students.csv"));
	        flatFileItemReader.setName("Text-Reader");
	        flatFileItemReader.setLinesToSkip(1);
	        flatFileItemReader.setLineMapper(lineMapper());
	        
	        
	        return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<Student> lineMapper(){
		
		DefaultLineMapper<Student> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		
			lineTokenizer.setDelimiter(",");
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames("id","name","marks");
	        
	        BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(Student.class);
	        
	        lineMapper.setLineTokenizer(lineTokenizer);
	        lineMapper.setFieldSetMapper(fieldSetMapper);
	        
	        return lineMapper;
	        
	  
		
	}
}
