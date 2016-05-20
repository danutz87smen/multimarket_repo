package com.dan.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpClientInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.amqp.OrderAmqpConfig;
import com.dan.entities.Order;
import com.dan.repositories.OrderRepository;
import com.dan.services.OrderService;
import com.dan.utils.AmqpConstants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private RabbitTemplate rabbitTamplate;
	
	private Map<String, Order>orders = new HashMap<>();

	@Override
	public Order getById(long id) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		
		Object receivedMessage = rabbitTamplate.convertSendAndReceive(AmqpConstants.PRODUCTS_REQUEST_BINDING, list , new MessagePostProcessor() {
			public Message postProcessMessage(Message message) throws AmqpException {
				message.getMessageProperties().setReplyTo(AmqpConstants.PRODUCTS_RESPONSE_QUEUE);
				try {
					message.getMessageProperties().setCorrelationId(UUID.randomUUID().toString().getBytes("UTF-8"));
				}
				catch (UnsupportedEncodingException e) {
					throw new AmqpException(e);
				}
				return message;
			}
		});
		//String foo = (String) rabbitTamplate.receiveAndConvert("myqueue");
		return repository.findOne(id);
	}

	@Override
	public List<Order> getOrders() {
		Iterator<Order> iterator = repository.findAll().iterator();
		List<Order> result = new ArrayList<>();
		while (iterator.hasNext()) {
			result.add(iterator.next());
		}
		return result;
	}

	@Override
	public void updateOrder(Order order) {
		repository.save(order);
	}

	@Override
	public Order saveOrder(Order order) {
		return repository.save(order);
	}

	@Override
	public void deleteOrderById(long id) {
		repository.delete(id);
	}
	
	@Override
	public Order createOrder(String userName) {
		Order newOrder = new Order();
		orders.put(userName, newOrder);
		return newOrder;
	}

	@Override
	public Order getByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByAccountId(long accountId) {
		return repository.findByAccountId(accountId);
	}
}
