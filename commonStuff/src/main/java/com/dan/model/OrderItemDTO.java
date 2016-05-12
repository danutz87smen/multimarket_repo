package com.dan.model;

public class OrderItemDTO {

	private long id;
	private ProductDTO product;
	private int quantity;
	private long tVAValue;
	private long total;


	protected long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}

	protected ProductDTO getProduct() {
		return product;
	}

	protected void setProduct(ProductDTO product) {
		this.product = product;
	}

	protected int getQuantity() {
		return quantity;
	}

	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected long getTotal() {
		return total;
	}

	protected void setTotal(long total) {
		this.total = total;
	}

	protected long gettVAValue() {
		return tVAValue;
	}

	protected void settVAValue(long tVAValue) {
		this.tVAValue = tVAValue;
	}
}