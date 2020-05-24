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
@EnableJpaRepositories(entityManagerFactoryRef = "teacherEntityManager", transactionManagerRef = "teacherTransactionManager", basePackages = "org.edu.cloud.notification.teacher")
public class TeacherDBConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.teacher.datasource")
	public HikariDataSource studentDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "teacherEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(studentDataSource()).properties(hibernateProperties())
				.packages("org.edu.cloud.notification.teacher").persistenceUnit("teacher").build();

	}

	@Bean(name = "teacherTransactionManager")
	public PlatformTransactionManager platformTransactionManager(
			@Qualifier("teacherEntityManager") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	private Map<String, String> hibernateProperties() {
		Resource resource = new ClassPathResource("hibernate.properties");
		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
					.collect(Collectors.toMap(p -> p.getKey().toString(), p -> p.getValue().toString()));
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
}
