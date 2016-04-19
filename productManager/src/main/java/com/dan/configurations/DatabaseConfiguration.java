package com.dan.configurations;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {
	// @Bean(initMethod = "migrate")
	// Flyway flyway() {
	// Flyway flayway = new Flyway();
	// flayway.setBaselineOnMigrate(true);
	// flayway.setLocations("classpath:db/migrationsSet");
	// flayway.setDataSource(dataSource());
	// return flyway();
	// }

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres");
		return dataSource;
	}
	
//	@Bean
//	public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
//		return new LazyConnectionDataSourceProxy(dataSource());
//	}
//
//	@Bean
//	public TransactionAwareDataSourceProxy transactionAwareDataSource() {
//		return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
//	}
//
//	@Bean
//	public DataSourceTransactionManager transactionManager() {
//		return new DataSourceTransactionManager(lazyConnectionDataSource());
//	}
//
//	@Bean
//	public DataSourceConnectionProvider connectionProvider() {
//		return new DataSourceConnectionProvider(transactionAwareDataSource());
//	}

	// @Bean
	// public JOOQToSpringExceptionTransformer
	// jooqToSpringExceptionTransformer() {
	// return new JOOQToSpringExceptionTransformer();
	// }

//	@Bean
//	public DefaultConfiguration configuration() {
//		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
//
//		jooqConfiguration.set(connectionProvider());
//		// jooqConfiguration.set(new DefaultExecuteListenerProvider(
//		// jooqToSpringExceptionTransformer()
//		// ));
//
//		SQLDialect dialect = SQLDialect.valueOf("Postgres");
//		jooqConfiguration.set(dialect);
//		return jooqConfiguration;
//	}
//
//	@Bean
//	public DefaultDSLContext dsl() {
//		return new DefaultDSLContext(configuration());
//	}
}
