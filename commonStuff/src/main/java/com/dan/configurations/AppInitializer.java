package com.dan.configurations;

import javax.servlet.Filter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dan.configurations.filters.SameOriginFilter;
@Configuration
@ComponentScan
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
	   
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	   
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }
	     
	    @Override
	    protected Filter[] getServletFilters() {
	        Filter [] singleton = { new SameOriginFilter() };
	        return singleton;
	    }

		@Override
		protected Class<?>[] getRootConfigClasses() {
			// TODO Auto-generated method stub
			return null;
		}
	  
}
