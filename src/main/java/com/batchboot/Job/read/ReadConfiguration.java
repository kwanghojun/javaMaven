package com.batchboot.Job.read;

import java.util.HashMap;
import java.util.List;

import javax.batch.api.chunk.ItemReader;
import javax.sql.DataSource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.mybatis.spring.batch.builder.MyBatisPagingItemReaderBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
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
	@StepScope
    public MyBatisPagingItemReader<testLowDto> read(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
    	System.out.println("{MyBatisPagingItemReader}..Start..!");
    	/**
        MyBatisPagingItemReader<testLowDto> myBatisPagingItemReader = new MyBatisPagingItemReader<testLowDto>();
        myBatisPagingItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisPagingItemReader.setQueryId("mybatis.testMapper.selectLowData");
        myBatisPagingItemReader.setPageSize(10); 
        
        SqlSession sqlSession= sqlSessionFactory.openSession(true);
        List<testLowDto> list=sqlSession.selectList("mybatis.testMapper.selectLowData",new testLowDto());
        System.out.println("list size.."+list.size());
        if(list !=null) {
        	for(int i=0; i < list.size();i++) {
        		testLowDto dto=list.get(i);
        		//System.out.println("dto Values..["+dto.toString()+"]");
        	}
        }
        **/
    	MyBatisPagingItemReader<testLowDto> myBatisPagingItemReader= new MyBatisPagingItemReaderBuilder<testLowDto>()
        		.sqlSessionFactory(sqlSessionFactory)
        		.queryId("mybatis.testMapper.selectLowData")
        		.parameterValues(new HashMap())
        		.pageSize(10)
        		.build();    	
        return myBatisPagingItemReader;
    }
	    
    

}
