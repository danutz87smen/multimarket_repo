package com.dan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.dao.impl.ProductDao;
import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.ProductDTO;
import com.dan.services.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;

	@Override
	public List<ProductDTO> getAll() {
		return dao.getProducts();
	}

	@Override
	public ProductDTO getById(int id) throws ModelNotFoundException {
		return dao.getProductById(id);
	}

	@Override
	public void update(ProductDTO product) {
		dao.updateProduct(product);

	}

	@Override
	public ProductDTO create(ProductDTO product) {
		return dao.createProduct(product);
	}

	@Override
	public void delete(int id) throws ModelNotFoundException {
		dao.deleteProductById(id);
	}
}
