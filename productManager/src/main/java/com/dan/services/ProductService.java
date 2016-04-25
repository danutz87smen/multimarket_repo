package com.dan.services;

import java.util.List;

import com.dan.model.ProductDTO;

public interface ProductService extends ModelService<ProductDTO> {

	List<ProductDTO> getProductsByCategoryId(int categoryId);

}
