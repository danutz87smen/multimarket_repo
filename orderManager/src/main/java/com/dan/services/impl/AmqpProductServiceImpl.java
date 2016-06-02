package com.dan.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dan.model.ProductDTO;
import com.dan.services.AmqpProductService;
import com.dan.utils.AmqpConstants;

@Component
public class AmqpProductServiceImpl implements AmqpProductService {
	@Autowired
	private RabbitTemplate rabbitTamplate;

	@Override
	public Map<Long, ProductDTO> getProductsByIds(Set<Long> prodIdSet) {
		Object receivedMessage = rabbitTamplate.convertSendAndReceive(AmqpConstants.PRODUCTS_REQUEST_EXCHANGE,
				AmqpConstants.PRODUCTS_REQUEST_ROUTING_KEY, prodIdSet, new MessagePostProcessor() {
					
					@Override
					public Message postProcessMessage(Message message) throws AmqpException {
						message.getMessageProperties().setHeader("content-type", "application/x-java-serialized-object");
						return message;
					}
				});

		return checkProductsList(receivedMessage);
	}

	@SuppressWarnings("unchecked")
	private Map<Long, ProductDTO> checkProductsList(Object receivedMessage) {
		Map<Long, ProductDTO> prodMap = null;

		try {
			prodMap = (Map<Long, ProductDTO>) receivedMessage;
		} catch (ClassCastException e) {
			e.printStackTrace();
			prodMap = new HashMap<Long, ProductDTO>();
		}
		return prodMap;
	}

}
