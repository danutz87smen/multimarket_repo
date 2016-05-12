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
	

	@RequestMapping(value = "/orders/", method = RequestMethod.GET)
	public ResponseEntity<List<OrderListDTO>> getAllOrders() {
		List<OrderListDTO> Orders = OrderService.getOrders();
		return new ResponseEntity<List<OrderListDTO>>(Orders, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderListDTO> getOrderById(@PathVariable int id) {
		OrderListDTO searchedOrder = null;
		searchedOrder = OrderService.getById(id);
		if (searchedOrder == null) {
			return new ResponseEntity<OrderListDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderListDTO>(searchedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders/", method = RequestMethod.POST)
	public ResponseEntity<OrderListDTO> createOrder(@RequestBody OrderListDTO order) {
		OrderService.saveOrder(order);
		return new ResponseEntity<OrderListDTO>(order, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/orders/{id}", method = RequestMethod.PUT)
	public ResponseEntity<OrderListDTO> updateOrder(@PathVariable int id, @RequestBody OrderListDTO order) {
		OrderListDTO originalOrder = OrderService.getById(id);
		if (originalOrder == null) {
			return new ResponseEntity<OrderListDTO>(HttpStatus.NOT_FOUND);
		}
		//todo
		OrderService.updateOrder(originalOrder);
		return new ResponseEntity<OrderListDTO>(originalOrder, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<OrderListDTO> deleteOrder(@PathVariable int id) {
		OrderService.deleteOrderById(id);
		return new ResponseEntity<OrderListDTO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/orders/", method = RequestMethod.GET, params="userName")
	public ResponseEntity<OrderListDTO> getOrderByUserName(@RequestParam long userName) {
		OrderListDTO searchedOrder = null;
	//	searchedOrder = OrderService.getByAccountId(userName);
		if (searchedOrder == null) {
			return new ResponseEntity<OrderListDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrderListDTO>(searchedOrder, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/orders/", method = RequestMethod.POST, params="userName")
	public ResponseEntity<OrderListDTO> createOrderByUsername(@RequestBody OrderListDTO Order) {
		OrderService.createOrder(null);
		return new ResponseEntity<OrderListDTO>(Order, HttpStatus.CREATED);
	}
}
