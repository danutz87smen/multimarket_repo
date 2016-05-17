package com.dan.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.entities.Order;
import com.dan.repositories.OrderRepository;
import com.dan.services.OrderService;

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
		//coada deja exista
		rabbitTamplate.convertAndSend("myqueue", "test dan");
		String foo = (String) rabbitTamplate.receiveAndConvert("myqueue");
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
