package com.dan.luncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EnableJpaRepositories("com.dan.repositories")
@EntityScan(basePackages = "com.dan.entities")
@ComponentScan(basePackages = "com.dan")
public class AppStarter {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppStarter.class, args);
	}
}
