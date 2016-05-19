package com.dan.amqp;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderAmqpConfig  {
	public static final String RECEIVING_QUEUE ="productResponse";
	public static final String SENDING_TOPIC ="productRequest";

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

	    @Bean
	    public RabbitTemplate rabbitTemplate() {
	        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
	        rabbitTemplate.setExchange(SENDING_TOPIC);
	        rabbitTemplate.setMessageConverter(jsonMessageConverter());
			return rabbitTemplate;
	    }
	    
		@Bean
		public MessageConverter jsonMessageConverter() {
			return new JsonMessageConverter();
		}
		
	    @Bean
	    public Queue myQueue() {
	       return new Queue(RECEIVING_QUEUE);
	    }
	    
	    @Bean
		public TopicExchange marketDataExchange() {
			return new TopicExchange(SENDING_TOPIC);
		}
}
