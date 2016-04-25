package com.dan.dao.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.InsertValuesStep4;
import org.jooq.Sequences;
import org.jooq.tables.Category;
import org.jooq.tables.Product;
import org.jooq.tables.ProductFeature;
import org.jooq.tables.records.ProductFeatureRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.ProductDTO;
import com.dan.model.ProductFeatureDTO;
import com.dan.util.DTOMappers;
import com.dao.intf.IProductDao;

@Repository
public class ProductDao implements IProductDao {
	@Autowired
	private DSLContext dlsContext;

	public List<ProductDTO> getProducts() {
		return dlsContext.fetch(Product.PRODUCT).map(DTOMappers.prodMapper);
	}

	public ProductDTO getProductById(int id) {
		return dlsContext.fetchOne(Product.PRODUCT, Product.PRODUCT.ID.eq(id)).map(DTOMappers.prodMapper);
	}

	public boolean updateProduct(ProductDTO product) {
		int updateRows = dlsContext.update(Product.PRODUCT).set(Product.PRODUCT.NAME, product.getName())
				.set(Product.PRODUCT.DESCRIPTION, product.getDescription())
				.set(Product.PRODUCT.CATGORY_ID, product.getCategoryId()).set(Product.PRODUCT.STOCK, product.getStock())
				.where(Product.PRODUCT.ID.eq(product.getId())).execute();
		return updateRows > 0;
	}

	public boolean deleteProduct(ProductDTO product) {
		int deletedRows = dlsContext.delete(Product.PRODUCT).where(Product.PRODUCT.ID.eq(product.getId())).execute();
		return deletedRows > 0;
	}

	public void deleteProductById(int id) {
		dlsContext.delete(Product.PRODUCT).where(Product.PRODUCT.ID.eq(id)).execute();
	}

	@Override
	public ProductDTO createProduct(ProductDTO product) {
		int id = dlsContext.nextval(Sequences.PRODUCT_SEQ).intValue();
		dlsContext
				.insertInto(Product.PRODUCT, Product.PRODUCT.ID, Product.PRODUCT.NAME, Product.PRODUCT.DESCRIPTION,
						Product.PRODUCT.CATGORY_ID, Product.PRODUCT.STOCK)
				.values(id, product.getName(), product.getDescription(), product.getCategoryId(), product.getStock())
				.returning(Product.PRODUCT.ID).fetchOne();
		product.setId(id);
		return product;
	}

	@Override
	public List<ProductDTO> getProductByCategoryId(int categoryId) {
		return dlsContext.select().from(Category.CATEGORY.join(Product.PRODUCT).onKey())
				.where(Category.CATEGORY.ID.eq(categoryId)).fetch().map(DTOMappers.prodMapper);
	}

	@Override
	public void addProductFeatures(List<ProductFeatureDTO> features) {
		if (features == null || features.isEmpty()) {
			return;
		}
		InsertValuesStep4<ProductFeatureRecord, Integer, Integer, Integer, String> insertInto = dlsContext.insertInto(ProductFeature.PRODUCT_FEATURE, ProductFeature.PRODUCT_FEATURE.ID,
				ProductFeature.PRODUCT_FEATURE.PRODUCT, ProductFeature.PRODUCT_FEATURE.FEATURE,
				ProductFeature.PRODUCT_FEATURE.FEATURE_VALUE);
		
		for(ProductFeatureDTO prodFeature: features){
			insertInto.values(prodFeature.getId(), prodFeature.getProduct(), prodFeature.getFeature().getId(), prodFeature.getFeatureValue());
		}
		insertInto.execute();
	}
}
