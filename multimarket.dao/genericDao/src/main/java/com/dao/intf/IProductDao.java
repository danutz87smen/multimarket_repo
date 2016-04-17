package com.dao.intf;

import java.util.List;

import com.dan.model.ProductDTO;

public interface IProductDao {

	List<ProductDTO> getProducts();

	ProductDTO getProductById(int id);

	boolean updateProduct(ProductDTO product);

	ProductDTO createProduct(ProductDTO product);

	boolean deleteProduct(ProductDTO product);

	void deleteProductById(int id);
}
