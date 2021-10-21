package com.example.springbatchexample4.config;

import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.springbatchexample4.batch.Processor;
import com.example.springbatchexample4.model.Employee;



@Configuration
@EnableBatchProcessing
//@EnableMongoRepositories
//@ComponentScan("com.example.springbatchexample4.batch")
public class BatchConfig {
	
	
	@Autowired
	  private MongoTemplate mongoTemplate;
	
	private Resource outputResource = new FileSystemResource("src/main/resources/employee.csv");

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<Employee> itemReader,ItemProcessor<Employee,Employee> itemProcessor,ItemWriter<Employee> itemWriter) {
		
		Step step=stepBuilderFactory.get("batch-step").<Employee,Employee>chunk(100).reader(itemReader).processor(itemProcessor)
				.writer(itemWriter).build();
		
		return jobBuilderFactory.get("batch-job").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
//	@Bean
//	public Step step() {
//		return stepBuilderFactory.get("batch-step").<Employee,Employee>chunk(100).reader(MongoItemReader<Employee>).processor(itemProcessor)
//				.writer(itemWriter).build();
//	}
	
	@Bean
	public MongoItemReader<Employee> itemReader(){
		
		MongoItemReader<Employee> reader = new MongoItemReader<Employee>();
		reader.setTemplate(mongoTemplate);
		 reader.setSort(new HashMap<String, Sort.Direction>() {{
		      put("_id", Direction.ASC);
		    }});
		reader.setTargetType(Employee.class);
		reader.setQuery("{}");
		return reader;
		
	}
	
	@Bean
    public FlatFileItemWriter<Employee> writer() 
    {
        //Create writer instance
        FlatFileItemWriter<Employee> writer = new FlatFileItemWriter<>();
         
        //Set output file location
        writer.setResource(outputResource);
         
        
        
        BeanWrapperFieldExtractor<Employee> fieldExtractor=new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] { "id", "name", "dept","salary" });
        
        DelimitedLineAggregator<Employee> lineAggregator=new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);
        
        
        writer.setLineAggregator(lineAggregator);
        

        return writer;
    }
}
