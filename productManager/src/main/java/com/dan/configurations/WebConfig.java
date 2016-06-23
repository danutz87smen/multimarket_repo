package com.dan.configurations;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dan.controllers.FileServlet;

@Configuration
public class WebConfig {
	@Bean
	public ServletRegistrationBean delegateServiceExporterServlet() {
	    return new ServletRegistrationBean(new FileServlet(), "/photos/*");
	}
}
