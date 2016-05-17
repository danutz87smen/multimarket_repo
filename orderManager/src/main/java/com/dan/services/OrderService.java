package com.dan.services;

import java.util.List;

import com.dan.entities.Order;

public interface OrderService {
	Order getById(long Id);
	List<Order> getOrders();
	void updateOrder(Order order);
	void deleteOrderById(long id);
	Order saveOrder(Order order);
	List<Order> getByAccountId(long accountId);
	//
	Order getByUserName(String userName);
	Order createOrder(String userName);
}
