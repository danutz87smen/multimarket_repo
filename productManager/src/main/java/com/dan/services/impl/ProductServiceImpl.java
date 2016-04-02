package com.dan.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.Product;
import com.dan.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("name", "description", 12));
		return products;
	}

	@Override
	public Product getById(long id) throws ModelNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product insert(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) throws ModelNotFoundException {
		// TODO Auto-generated method stub

	}

}
