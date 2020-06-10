package com.simpleJwt.server.SimpleJwtServer.datasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(	entityManagerFactoryRef = "mongoEntityManagerFactory",
						basePackages = { 
											"com.simpleJwt.server.SimpleJwtServer.reposMongpDB"
										})
public class MongoDBConfig {

	
	@Bean(name = "mongoDataSource")
	@ConfigurationProperties (prefix = "spring.mongo.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	
	@Bean( name = "mongoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean (EntityManagerFactoryBuilder entityManagerFactoryBuilder,
			@Qualifier("mongoDataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap();
		properties.put("hibernate.dialect", "org.hibernate.ogm.datastore.mongodb.MongoDBDialect");
		return entityManagerFactoryBuilder.dataSource(dataSource)
				.properties(properties)
				.packages("com.simpleJwt.server.SimpleJwtServer.reposMongpDB")
				.persistenceUnit("learning")
				.build();
		
	}
	
	
	@Bean( name = "mongoTransactionManager")
	public PlatformTransactionManager platformTransactionManager( @Qualifier("mongoEntityManagerFactory") EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	
	@ConfigurationProperties(prefix = "mongodb")
	public MongoProperties getSecondary() {
	    return new MongoProperties();
	}


	
}
