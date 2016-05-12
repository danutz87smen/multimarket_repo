package com.dan.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderListDTO {

	private int id;
	private int accountId;
	private Date orderDate;
	private long totalPrice;
	private long tVAValue;
	
	private OrderState orderStare = OrderState.OPEN;
	
	private List<OrderItemDTO> items = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long gettVAValue() {
		return tVAValue;
	}

	public void settVAValue(long tVAValue) {
		this.tVAValue = tVAValue;
	}

	public List<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}

	public OrderState getOrderStare() {
		return orderStare;
	}

	public void setOrderStare(OrderState orderStare) {
		this.orderStare = orderStare;
	}
}