package com.dao.intf;

import java.util.List;

import com.dan.model.OrderListDTO;



public interface IOrderDao {

	List<OrderListDTO> getOrders();

	OrderListDTO getOrderById(int id);

	boolean updateOrder(OrderListDTO order);

	OrderListDTO createOrder(OrderListDTO order);

	boolean deleteOrder(OrderListDTO order);

	void deleteOrderById(int id);
}
