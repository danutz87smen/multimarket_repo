package com.dan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dan.entities.Order;
import com.dan.services.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService OrderService;
	
	@RequestMapping(value = "/orders/", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> Orders = OrderService.getOrders();
		return new ResponseEntity<List<Order>>(Orders, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrderById(@PathVariable long id) {
		Order searchedOrder = null;
		searchedOrder = OrderService.getById(id);
		if (searchedOrder == null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Order>(searchedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders/", method = RequestMethod.GET, params = "accountId")
	public ResponseEntity<List<Order>> getOrdersByUserName(@RequestParam long accountId) {
		List<Order> searchedOrders = null;
		searchedOrders = OrderService.getByAccountId(accountId);
		if (searchedOrders == null || searchedOrders.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(searchedOrders, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/", method = RequestMethod.POST)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		OrderService.saveOrder(order);
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order order) {
		Order originalOrder = OrderService.getById(id);
		if (originalOrder == null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		
		OrderService.updateOrder(order);
		return new ResponseEntity<Order>(originalOrder, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Order> deleteOrder(@PathVariable int id) {
		Order originalOrder = OrderService.getById(id);
		if (originalOrder == null) {
			return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		}
		OrderService.deleteOrderById(id);
		return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
	}

//	@RequestMapping(value = "/orders/", method = RequestMethod.POST, params="userName")
//	public ResponseEntity<Order> createOrderByUsername(@RequestBody Order Order) {
//		OrderService.createOrder(null);
//		return new ResponseEntity<Order>(Order, HttpStatus.CREATED);
//	}
}
