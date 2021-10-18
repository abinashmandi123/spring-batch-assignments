package com.example.springbatchassignment6.config;

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
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.springbatchassignment6.model.Text;




@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TextRowMapper rowMapper;
	
	private Resource outputResource=new FileSystemResource("src/main/resources/data.txt");

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<Text> itemReader,ItemProcessor<Text,Text> itemProcessor,ItemWriter<Text> itemWriter) {
		
		Step step=stepBuilderFactory.get("batch-step").<Text,Text>chunk(100).reader(itemReader).processor(itemProcessor)
				.writer(itemWriter).build();
		
		return jobBuilderFactory.get("batch-job").incrementer(new RunIdIncrementer()).start(step).build();
	}
	
	@Bean 
	public JdbcCursorItemReader<Text> itemReader() {
		JdbcCursorItemReader<Text> reader=new JdbcCursorItemReader<>();
		
		reader.setDataSource(dataSource);
		reader.setSql("select data from text");
		reader.setRowMapper(rowMapper);
		
		return reader;
	}
	
	
	
	@Bean
    public FlatFileItemWriter<Text> writer() 
    {
        //Create writer instance
        FlatFileItemWriter<Text> writer = new FlatFileItemWriter<>();
         
        //Set output file location
        writer.setResource(outputResource);
         
        
        
        BeanWrapperFieldExtractor<Text> fieldExtractor=new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] { "data" });
        
        DelimitedLineAggregator<Text> lineAggregator=new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        
        
        writer.setLineAggregator(lineAggregator);
         

        return writer;
    }
}
