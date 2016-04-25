package com.dan.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
	private int id;
	private String name;
	private String description;
	private int stock;
	private List<ProductFeatureDTO> features = new ArrayList<>();
	private Integer categoryId;

	public ProductDTO() {
		super();
	}

	public List<ProductFeatureDTO> getFeatures() {
		return features;
	}

	public void setFeatures(List<ProductFeatureDTO> features) {
		this.features = features;
	}

	public ProductDTO(int id, String name, String description, Integer cat, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = cat;
		this.stock = stock;
	}
	
	public ProductDTO( String name, String description, Integer cat, int stock) {
		super();
		this.name = name;
		this.description = description;
		this.categoryId = cat;
		this.stock = stock;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
