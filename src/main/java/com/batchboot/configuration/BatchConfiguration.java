package com.batchboot.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchboot.Incrementer.CurrentTimeIncrementer;
import com.batchboot.listener.ChunkExecutionListener;
import com.batchboot.listener.JobCompletionNotificationListener;
import com.batchboot.listener.StepExecutionNotificationListener;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {
	
	
	public int CORE_TASK_POOL_SIZE = Runtime.getRuntime().availableProcessors();
	public int MAX_TASK_POOL_SIZE = 128;
	public int CHUNK_AND_PAGE_SIZE = 400;
	
	// This would reside in your BatchConfigurer implementation
	@Autowired
	@Qualifier("dbDatasource")
	public DataSource dbDatasource;
	
	@Autowired
	@Qualifier("aTX")
	public PlatformTransactionManager transactionManager;
	
	@Override
	protected JobRepository createJobRepository() throws Exception {
	    JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
	    factory.setDataSource(dbDatasource);
	    factory.setIsolationLevelForCreate("ISOLATION_DEFAULT");
	    factory.setTransactionManager(transactionManager);
	    factory.setTablePrefix("BATCH_");
	    factory.setMaxVarCharLength(1000);
	    return factory.getObject();
	}
	
	@Bean(name = "nomalTaskPool")
    public TaskExecutor nomalTaskPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_TASK_POOL_SIZE);
        executor.setMaxPoolSize(MAX_TASK_POOL_SIZE);
        return executor;
    }
 
}
