package com.dan.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dan.model.ProductDTO;

public interface ProductService extends ModelService<ProductDTO> {

	List<ProductDTO> getProductsByCategoryId(int categoryId);

	Map<Long, ProductDTO> getProductsByIds(Set<Long> ids);
}
