package com.batchboot.Job.read;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ReadConfiguration {
	

	@Bean(name ="myBatisPagingItemReader")
    public MyBatisPagingItemReader<testLowDto> read(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	System.out.println("{MyBatisPagingItemReader}..Start..!");
        MyBatisPagingItemReader<testLowDto> myBatisPagingItemReader = new MyBatisPagingItemReader<testLowDto>();
        myBatisPagingItemReader.setQueryId("selectLowData");
        myBatisPagingItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisPagingItemReader.setPageSize(10);
        try {
        	System.out.println("sqlSessionFactory..["+sqlSessionFactory+"]");
        	/**
        	testLowDto testDto=myBatisPagingItemReader.read();
        	if(testDto !=null) {
        		System.out.println("Read..testLowDto..["+testDto.toString()+"]");
        	}
        	**/
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return myBatisPagingItemReader;
    }
	    
    

}
