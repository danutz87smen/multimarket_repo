package com.dan.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.ProductDTO;
import com.dan.services.ProductService;
import com.dao.intf.IProductDao;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private IProductDao dao;

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
		product.setId(dao.createProduct(product).getId());
		dao.addProductFeatures(product.getFeatures());
		return product;
	}

	@Override
	public void delete(int id) throws ModelNotFoundException {
		dao.deleteProductById(id);
	}

	@Override
	public List<ProductDTO> getProductsByCategoryId(int categoryId) {
		return dao.getProductByCategoryId(categoryId);
	}

	@Override
	public Map<Long, ProductDTO> getProductsByIds(Set<Long> ids) {
		return dao.getProductsByIds(ids);
	}
}
