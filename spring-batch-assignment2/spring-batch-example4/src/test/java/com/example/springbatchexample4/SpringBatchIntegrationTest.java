package com.example.springbatchexample4;



import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.example.springbatchexample4.config.BatchConfig;
import com.example.springbatchexample4.controller.BatchController;
import com.example.springbatchexample4.model.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBatchTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ContextConfiguration(classes = { BatchConfig.class })
@EnableMongoRepositories
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, 
  DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class SpringBatchIntegrationTest {

	@InjectMocks
	private BatchController batchController;
	
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
	
	@MockBean
	private MongoTemplate mongoTemplate;
	
	@MockBean
	private ItemProcessor<Employee,Employee> processor;
  
	
	
	
	@Before
	public void setUp() {
	MockitoAnnotations.initMocks(this);
	}


	@Test
    public void launchJob() throws Exception {

//	//testing a job
//        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
//        
//	//Testing a individual step
//        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
//        
//        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		assertNotNull(batchController.load());
		
        
    }
}
