package com.batchboot.Job.ChunkBatchJob.write;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchboot.dto.testLowDto;

@Configuration
public class WriteConfiguration {
    
    @Bean(name ="myBatisBatchItemWriter")
	@StepScope
    public MyBatisBatchItemWriter writer(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory){
    	System.out.println("{MyBatisBatchItemWriter}..Start..!");
        MyBatisBatchItemWriter<testLowDto> myBatisBatchItemWriter = new MyBatisBatchItemWriterBuilder<testLowDto>()
        											.sqlSessionFactory(sqlSessionFactory)
        											.statementId("mybatis.testMapper.insertLowData2")
        											.build();
        System.out.println("{MyBatisBatchItemWriter}..End..!");
        return myBatisBatchItemWriter;
    }
}
