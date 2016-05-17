package com.dan.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dan.entities.state.OrderState;

@Entity
@Table(name = "ORDER_SHOP")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long accountId;
	private Date orderDate;
	private long totalPrice;
	private long tvaValue;

	@Enumerated(EnumType.STRING)
	private OrderState orderStare = OrderState.OPEN;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private List<Item> items = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
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
		return tvaValue;
	}

	public void settVAValue(long tVAValue) {
		this.tvaValue = tVAValue;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public OrderState getOrderStare() {
		return orderStare;
	}

	public void setOrderStare(OrderState orderStare) {
		this.orderStare = orderStare;
	}
}