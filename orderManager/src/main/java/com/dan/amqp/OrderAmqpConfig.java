package com.dan.amqp;

import javax.validation.constraints.NotNull;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dan.configurations.GenericAmqpConfig;
import com.dan.utils.AmqpConstants;

@Configuration
public class OrderAmqpConfig  extends GenericAmqpConfig {
		
	    @Bean
	    public Queue myQueue() {
	       return new Queue(AmqpConstants.PRODUCTS_RESPONSE_QUEUE);
	    }
	    
	    @Bean
		public TopicExchange marketDataExchange() {
			return new TopicExchange(AmqpConstants.PRODUCTS_REQUEST_EXCHANGE);
		}

		@Override
		protected void setTemplateSeettings(@NotNull RabbitTemplate rabbitTemplate) {
			rabbitTemplate.setExchange(AmqpConstants.PRODUCTS_REQUEST_EXCHANGE);
			rabbitTemplate.setQueue(AmqpConstants.PRODUCTS_RESPONSE_QUEUE);
		}
}
