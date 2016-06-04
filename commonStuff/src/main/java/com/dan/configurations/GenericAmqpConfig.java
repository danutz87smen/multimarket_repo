package com.dan.configurations;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class GenericAmqpConfig  {

	 @Bean
	    public CachingConnectionFactory connectionFactory() {
	        CachingConnectionFactory connectionFactory =
	            new CachingConnectionFactory("localhost");
	        return connectionFactory;
	    }

	    @Bean
	    public AmqpAdmin amqpAdmin() {
	        return new RabbitAdmin(connectionFactory());
	    }
	    
	    protected abstract void setTemplateSeettings(RabbitTemplate rabbitTemplate);
	    
	    @Bean
	    public RabbitTemplate rabbitTemplate() {
	        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
	      //  rabbitTemplate.setMessageConverter(jsonMessageConverter());
	        setTemplateSeettings(rabbitTemplate);
			return rabbitTemplate;
	    }
//	    
//		@Bean
//		public MessageConverter jsonMessageConverter() {
//			return new JsonMessageConverter();
//		}
}
