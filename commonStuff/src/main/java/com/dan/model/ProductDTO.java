package com.dan.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String description;
	private int stock;
	private List<ProductFeatureDTO> features = new ArrayList<>();
	private Long categoryId;
	private long price;
	private String photos;

	public ProductDTO() {
		super();
	}

	public List<ProductFeatureDTO> getFeatures() {
		return features;
	}

	public void setFeatures(List<ProductFeatureDTO> features) {
		this.features = features;
	}

	public ProductDTO(long id, String name, String description, Long cat, int stock, long price, String photos) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = cat;
		this.stock = stock;
		this.price = price;
		this.photos = photos;
	}

	public ProductDTO(String name, String description, Long cat, int stock, long price) {
		super();
		this.name = name;
		this.description = description;
		this.categoryId = cat;
		this.stock = stock;
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
