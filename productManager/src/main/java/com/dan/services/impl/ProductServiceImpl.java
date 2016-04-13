package com.dan.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.dao.ProductDao;
import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.Product;
import com.dan.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;

	@Override
	public List<Product> getAll() {
		return dao.getProducts();
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
