package com.dao.intf;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dan.model.ProductDTO;
import com.dan.model.ProductFeatureDTO;

public interface IProductDao {

	List<ProductDTO> getProducts();

	ProductDTO getProductById(long id);

	boolean updateProduct(ProductDTO product);

	ProductDTO createProduct(ProductDTO product);

	boolean deleteProduct(ProductDTO product);

	void deleteProductById(long id);

	List<ProductDTO> getProductByCategoryId(long categoryId);

	void addProductFeatures(List<ProductFeatureDTO> features);

	Map<Long, ProductDTO> getProductsByIds(Set<Long> ids);
}
