package com.example.springbatcheaxample11.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.amqp.AmqpItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.springbatcheaxample11.model.Student;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	

	 public final static String queueName = "student_queue";
	 
	 private Resource outputResource = new FileSystemResource("src/main/resources/student.txt");
	 
	 @Autowired
	  private StepBuilderFactory stepBuilderFactory;


	  @Autowired
	  private ConnectionFactory rabbitConnectionFactory;
	  
	  
//	  @Bean
//	  public Step getMyJobStep() {
//	    return this.stepBuilderFactory.get("myJobStep")
//	        .<Student, Student>chunk(1)
//	        .reader(this.getMyReader())
//	        .processor(processor())
//	        .writer(this.writer())
//	        .build();
//	  }

	  @Bean
		public Job job(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,ItemReader<String> itemReader,ItemProcessor<String,String> itemProcessor,
				ItemWriter<String> itemWriter) {
			
			Step step=stepBuilderFactory.get("batch-step").<String,String>chunk(100).reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
			
			return jobBuilderFactory.get("batch-job").incrementer(new RunIdIncrementer()).start(step).build();
			
		}
	  // this will create a new queue if it doesn't exist; otherwise, it'll use the existing one of the same name
	  // ...the second argument means to make the queue 'durable'
	  @Bean
	  public Queue myQueue() {
	    return new Queue(queueName, true);
	  }

	  // this is necessary for operations with Spring AMQP
	  @Bean
	  public RabbitTemplate getMyQueueTemplate() {
	    RabbitTemplate template = new RabbitTemplate(this.rabbitConnectionFactory);
	    template.setDefaultReceiveQueue(queueName);

	    return template;
	  }
	  
	  @Bean
	  public ItemReader<String> getMyReader() {
	    return new AmqpItemReader<String>(this.getMyQueueTemplate());
	  }



	  @Bean
	    public FlatFileItemWriter<String> writer() 
	    {
	        //Create writer instance
	        FlatFileItemWriter<String> writer = new FlatFileItemWriter<>();
	         
	        //Set output file location
	        writer.setResource(outputResource);
	         
	        
	        
//	        BeanWrapperFieldExtractor<String> fieldExtractor=new BeanWrapperFieldExtractor<>();
//	        fieldExtractor.setNames(new String[] { "id", "name", "marks" });
	        
	        DelimitedLineAggregator<String> lineAggregator=new DelimitedLineAggregator<>();
//	        lineAggregator.setDelimiter(",");
//	        lineAggregator.setFieldExtractor(fieldExtractor);
	        
	        
	        writer.setLineAggregator(lineAggregator);
	        

	        return writer;
	    }
}
