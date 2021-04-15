package com.batchboot.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
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

@Configuration
public class DbConfiguration {

    @Bean(name = "dbDatasource")
    @Primary
    @ConfigurationProperties(prefix = "spring.db.datasource")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionProductFactory(@Qualifier("dbDatasource") DataSource datasorce, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(datasorce);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/mybatis-config2.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/sqlmapper/*.xml"));
        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "sqlSession")
    public SqlSessionTemplate sqlSessionProduct(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Primary
    @Bean(name = "aTX")
    public PlatformTransactionManager ProductTransactionManager(@Qualifier("dbDatasource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
       

}
