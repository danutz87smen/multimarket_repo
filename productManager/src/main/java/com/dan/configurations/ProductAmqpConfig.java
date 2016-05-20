package com.dan.configurations;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dan.utils.AmqpConstants;

@Configuration
public class ProductAmqpConfig extends GenericAmqpConfig  {

	    @Bean 
		public SimpleMessageListenerContainer messageListenerContainer() {
			SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());		
			container.setQueueNames(AmqpConstants.PRODUCTS_REQUEST_QUEUE);
			container.setMessageListener(messageListenerAdapter());
			container.setAcknowledgeMode(AcknowledgeMode.AUTO);
			return container;

			//container(using(connectionFactory()).listenToQueues(marketDataQueue(), traderJoeQueue()).withListener(messageListenerAdapter()).
		}
		
		@Bean 
		public MessageListenerAdapter messageListenerAdapter() {
			 MessageListenerAdapter adapter = new MessageListenerAdapter(productHandler(), 
					jsonMessageConverter());
			 adapter.setResponseRoutingKey(AmqpConstants.PRODUCTS_RESPONSE_QUEUE);
			 return adapter;
		}
		
		@Bean
		public ProductHandler productHandler(){
			return new ProductHandler();
		}
	    
		
//	    @Bean
//	    public Queue requestProductQueue() {
//	       return new Queue(AmqpConstants.PRODUCTS_REQUEST_QUEUE);
//	    }
	    
	    @Bean
		public TopicExchange productExchange() {
			return new TopicExchange(AmqpConstants.PRODUCTS_REQUEST_EXCHANGE);
		}
	    
	    @Bean
		public Binding marketDataBinding() {		
			return BindingBuilder.bind(requestProductQueue()).to(productExchange())
					.with(AmqpConstants.PRODUCTS_REQUEST_BINDING);
		}

		@Override
		protected void setTemplateSeettings(RabbitTemplate rabbitTemplate) {
			rabbitTemplate.setQueue(AmqpConstants.PRODUCTS_REQUEST_QUEUE);			
		}
}
