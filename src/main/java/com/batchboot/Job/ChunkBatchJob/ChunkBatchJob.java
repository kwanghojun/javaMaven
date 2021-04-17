package com.batchboot.Job.ChunkBatchJob;

import com.batchboot.Incrementer.CurrentTimeIncrementer;
import com.batchboot.Job.ChunkBatchJob.process.ChunkTestItemProcessor;
import com.batchboot.dao.testDao;
import com.batchboot.dto.testLowDto;
import com.batchboot.listener.JobCompletionNotificationListener;
import com.batchboot.listener.StepExecutionNotificationListener;

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
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class ChunkBatchJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;


	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("nomalTaskPool")
    public TaskExecutor nomalTaskPool;
	
	
	@Autowired
	@Qualifier("aTX")
	public PlatformTransactionManager transactionManager;
	
	@Autowired
	public testDao testdao;
	
	
	@Bean(name="testLowChunkJob")
	public Job testLowChunkJob(JobCompletionNotificationListener listener, Step stepChunk1,Step initStep1) {
		System.out.println("{testLowChunkJob}..Start..!");
		Job reJob=jobBuilderFactory.get("testLowChunkJob")
				.incrementer(new CurrentTimeIncrementer()).start(initStep1).next(stepChunk1).build();
		System.out.println("{testLowChunkJob}..End..!");
		return reJob;
	}
	
   @Bean(name="initStep1")
   public Step initStep1(Tasklet inittasklet1) {
		 System.out.println("initstep1.. Start..!"); 
       return stepBuilderFactory.get("initStep1").tasklet(inittasklet1).build();
   }
	 
	 
	@Bean
    public Tasklet inittasklet1() {
		System.out.println("inittasklet1.. Start..!"); 
        return (contribution, chunkContext)->{
        	Map paramMap=new HashMap();        	
            int delCnt=testdao.deleteLowDataCopy(paramMap);
            System.out.println("delCnt.. ["+delCnt+"]"); 
            return RepeatStatus.FINISHED;
        };
    }
	
	@Bean
	public Step stepChunk1(
			@Qualifier("myBatisPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader,
			@Qualifier("myBatchProcess") ItemProcessor myBatchProcess,
			@Qualifier("myBatisBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter,
			StepExecutionNotificationListener stepExecutionNotificationListener
			) {
		System.out.println("{stepChunk1}..Start..!");
		return stepBuilderFactory.get("stepChunk1") 
				.transactionManager(transactionManager)
				.listener(stepExecutionNotificationListener)
				.chunk(10)
				.reader(myBatisPagingItemReader)
				.processor(myBatchProcess)
				.writer(myBatisBatchItemWriter)
				.taskExecutor(nomalTaskPool)
				.build();
	}
	
	
}
