package com.simpleJwt.server.SimpleJwtServer.datasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(	entityManagerFactoryRef = "stgEntityManagerFactory",
						basePackages = { 
											"com.simpleJwt.server.SimpleJwtServer.reposStgDB"
										})
public class STGConfig {

	
	@Bean(name = "stgDataSource")
	@ConfigurationProperties (prefix = "spring.stg.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	
	@Bean( name = "stgEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean (EntityManagerFactoryBuilder entityManagerFactoryBuilder,
			@Qualifier("stgDataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap();
		properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		return entityManagerFactoryBuilder.dataSource(dataSource)
				.properties(properties)
				.packages("com.simpleJwt.server.SimpleJwtServer.reposStgDB")
				.persistenceUnit("STG_DB")
				.build();
		
	}
	
	
	@Bean( name = "stgTransactionManager")
	public PlatformTransactionManager platformTransactionManager( @Qualifier("stgEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	
	
}
