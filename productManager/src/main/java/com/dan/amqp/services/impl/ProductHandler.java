package com.dan.amqp.services.impl;

import java.util.Map;
import java.util.Set;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dan.model.ProductDTO;
import com.dan.services.ProductService;
import com.dan.utils.AmqpConstants;

@Component
public class ProductHandler {

	@Autowired
	private ProductService productService;

	public ProductHandler() {
	}

	public ProductHandler(ProductService productService) {
		super();
		this.productService = productService;
	}

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = AmqpConstants.PRODUCTS_REQUEST_QUEUE) ,
			exchange = @Exchange(value = AmqpConstants.PRODUCTS_REQUEST_EXCHANGE, type = ExchangeTypes.TOPIC) , 
			key = AmqpConstants.PRODUCTS_REQUEST_ROUTING_KEY) )
	public Map<Long, ProductDTO> handleMessage(Set<Long> productIds) {
		return productService.getProductsByIds(productIds);
	}
}
