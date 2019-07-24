package com.rht.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Mybatis配置
 */
@Configuration
@MapperScan("com.rht.dao")	// 扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;


  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setTypeAliasesPackage("com.rht.model");	// 扫描Model
    System.out.println("********************************");
    
	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	sessionFactory.setMapperLocations(resolver.getResources("classpath:*.*.sqlmap.*.xml"));	// 扫描映射文件


    System.out.println("111111111111111**");
    System.out.println(sessionFactory);
    return sessionFactory.getObject();
  }
}