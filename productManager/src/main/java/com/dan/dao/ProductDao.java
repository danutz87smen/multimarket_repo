package com.dan.dao;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.tables.records.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.Product;

@Repository
public class ProductDao {
	@Autowired
	private DSLContext dlsContext;

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		Product prod = null;
		for (ProductRecord product : dlsContext.fetch(org.jooq.tables.Product.PRODUCT)) {
			prod = new Product();
			prod.setId(product.getId());
			prod.setName(product.getName());
			prod.setDescription(product.getDescription());
			prod.setStock(product.getStock());
			products.add(prod);
		}
		return products;
	}
}
