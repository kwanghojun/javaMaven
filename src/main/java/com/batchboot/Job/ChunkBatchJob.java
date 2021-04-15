package com.batchboot.Job;

import com.batchboot.Incrementer.CurrentTimeIncrementer;
import com.batchboot.Job.process.ChunkTestItemProcessor;
import com.batchboot.dto.testLowDto;
import com.batchboot.listener.JobCompletionNotificationListener;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ChunkBatchJob {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;


	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step stepChunk1(
			@Qualifier("myBatisPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader,
			@Qualifier("myBatchProcess") ItemProcessor myBatchProcess,
			@Qualifier("myBatisBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter) {
		System.out.println("{stepChunk1}..Start..!");
		return stepBuilderFactory.get("stepChunk1").chunk(10).reader(myBatisPagingItemReader)
				.processor(myBatchProcess).writer(myBatisBatchItemWriter).build();
	}
	
	@Bean("myBatchProcess")
	//@StepScope
	public ItemProcessor mySqlBatchProcess() {
		return new ChunkTestItemProcessor();
	}

	@Bean(name="testLowChunkJob")
	public Job testLowChunkJob(JobCompletionNotificationListener listener, Step stepChunk1) {
		System.out.println("{testLowChunkJob}..Start..!");
		return jobBuilderFactory.get("testLowChunkJob").incrementer(new CurrentTimeIncrementer())
				.listener(listener).flow(stepChunk1).end().build();
	}
	
}
