package org.edu.cloud.notification.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "taskEntityManager", transactionManagerRef = "taskTransactionManager", basePackages = "org.edu.cloud.noification")
public class TaskDBConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public HikariDataSource taskDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "taskEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(taskDataSource()).properties(hibernateProperties())
				.packages("org.edu.cloud.noification").persistenceUnit("task").build();
	}

	@Primary
	@Bean(name = "taskTransactionManager")
	public PlatformTransactionManager platformTransactionManager(
			@Qualifier("taskEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, Object> hibernateProperties() {
		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			Map<String, Object> collect = properties.entrySet().stream()
					.collect(Collectors.toMap(p -> p.getKey().toString(), p -> p.getValue()));
			return collect;
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
}
