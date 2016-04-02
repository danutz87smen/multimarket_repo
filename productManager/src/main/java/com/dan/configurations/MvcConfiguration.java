package com.dan.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.dan")
public class MvcConfiguration extends WebMvcConfigurerAdapter {
	
	     
//	    @Override
//	    public void configureViewResolvers(ViewResolverRegistry registry) {
//	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	        viewResolver.setViewClass(JstlView.class);
//	        viewResolver.setPrefix("/WEB-INF/views/");
//	        viewResolver.setSuffix(".jsp");
//	        registry.viewResolver(viewResolver);
//	    }
//	 
//	    @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//	    }
	 
}
