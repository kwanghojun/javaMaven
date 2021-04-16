package com.batchboot.Job2;

import com.batchboot.Incrementer.CurrentTimeIncrementer;
import com.batchboot.Job.process.ChunkTestItemProcessor;
import com.batchboot.dao.testDao;
import com.batchboot.dto.testLowDto;
import com.batchboot.listener.JobCompletionNotificationListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class taskletBatchJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;


	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	public testDao testdao;
	
	@Bean(name="testTaskLetJob")
	public Job testTaskLetJob(JobCompletionNotificationListener listener, Step taskletstep1) {
		System.out.println("{testTaskLetJob}..Start..!");
		Job reJob=jobBuilderFactory.get("testTaskLetJob")
				.incrementer(new CurrentTimeIncrementer()).flow(taskletstep1).end().build();
		return reJob;
	}
	
	 @Bean
    public Step taskletstep1(Tasklet tasklet1) {
		 System.out.println("taskletstep1.. Start..!"); 
        return stepBuilderFactory.get("taskletstep1").tasklet(tasklet1).build();
    }
	 
	@Bean
    public Tasklet tasklet1() {
		System.out.println("tasklet1.. Start..!"); 
        return (contribution, chunkContext)->{
        	Map paramMap=new HashMap();        	
            List<testLowDto> getlist=testdao.selectTest(paramMap);
            for(int i=0; i < getlist.size();i++) {
            	testLowDto getDto=getlist.get(i);
            	System.out.println("get Dto..getCol1 .["+getDto.toString()+"]");
            }
            
            return RepeatStatus.FINISHED;
        };
    }
	
	
}
