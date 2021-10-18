package com.example.springbatchassignment5.config;

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
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;


import com.example.springbatchassignment5.model.Text;



@Configuration

public class BatchConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<Text> itemReader,ItemProcessor<Text,Text> itemProcessor,ItemWriter<Text> itemWriter) {
		
		Step step=stepBuilderFactory.get("batch-step").<Text,Text>chunk(100).reader(itemReader).processor(itemProcessor)
				.writer(itemWriter).build();
		
		return jobBuilderFactory.get("batch-job").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
	@Bean
	public FlatFileItemReader<Text> itemReader(){
		
		   FlatFileItemReader<Text> flatFileItemReader = new FlatFileItemReader<>();
	        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/data.txt"));
	        flatFileItemReader.setName("Text-Reader");
	        flatFileItemReader.setLineMapper(lineMapper());
	        
	        
	        return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<Text> lineMapper(){
		
		DefaultLineMapper<Text> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
		
			
	        lineTokenizer.setStrict(false);
	        lineTokenizer.setNames("data");
	        
	        BeanWrapperFieldSetMapper<Text> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
	        fieldSetMapper.setTargetType(Text.class);
	        
	        lineMapper.setLineTokenizer(lineTokenizer);
	        lineMapper.setFieldSetMapper(fieldSetMapper);
	        
	        return lineMapper;
	        
	  
		
	}
	
	@Bean
	public JdbcBatchItemWriter<Text> writer(){
		JdbcBatchItemWriter<Text> writer = new JdbcBatchItemWriter<Text>();
		writer.setDataSource(dataSource);
		writer.setSql("INSERT INTO text(data) VALUES(?)");
		writer.setItemPreparedStatementSetter(new TextPreparedStatementSetter());
		
		
		return writer;
	}
}
