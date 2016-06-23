package com.dan.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jooq.DSLContext;
import org.jooq.InsertValuesStep4;
import org.jooq.Sequences;
import static org.jooq.tables.Category.*;
import static org.jooq.tables.Product.*;
import static org.jooq.tables.ProductFeature.*;
import org.jooq.tables.records.ProductFeatureRecord;
import org.jooq.tables.records.ProductRecord;
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
		return dlsContext.fetch(PRODUCT).map(DTOMappers.prodMapper);
	}

	public ProductDTO getProductById(long id) {
		ProductRecord fetchOne = dlsContext.fetchOne(PRODUCT, PRODUCT.ID.eq(id));
		if (fetchOne == null) {
			return null;
		}
		return fetchOne.map(DTOMappers.prodMapper);
	}

	public boolean updateProduct(ProductDTO product) {
		int updateRows = dlsContext.update(PRODUCT).set(PRODUCT.NAME, product.getName())
				.set(PRODUCT.DESCRIPTION, product.getDescription()).set(PRODUCT.CATGORY_ID, product.getCategoryId())
				.set(PRODUCT.STOCK, product.getStock()).set(PRODUCT.PRICE, product.getPrice())
				.where(PRODUCT.ID.eq(product.getId())).execute();
		return updateRows > 0;
	}

	public boolean deleteProduct(ProductDTO product) {
		int deletedRows = dlsContext.delete(PRODUCT).where(PRODUCT.ID.eq(product.getId())).execute();
		return deletedRows > 0;
	}

	public void deleteProductById(long id) {
		dlsContext.delete(PRODUCT).where(PRODUCT.ID.eq(id)).execute();
	}

	@Override
	public ProductDTO createProduct(ProductDTO product) {
		long id = dlsContext.nextval(Sequences.PRODUCT_SEQ).intValue();
		dlsContext
				.insertInto(PRODUCT, PRODUCT.ID, PRODUCT.NAME, PRODUCT.DESCRIPTION, PRODUCT.CATGORY_ID, PRODUCT.STOCK,
						PRODUCT.PRICE, PRODUCT.PHOTOS)
				.values(id, product.getName(), product.getDescription(), product.getCategoryId(), product.getStock(),
						product.getPrice(), product.getPhotos())
				.returning(PRODUCT.ID).fetchOne();
		product.setId(id);
		return product;
	}

	@Override
	public List<ProductDTO> getProductByCategoryId(long categoryId) {
		return dlsContext.select().from(CATEGORY.join(PRODUCT).onKey()).where(CATEGORY.ID.eq(categoryId)).fetch()
				.map(DTOMappers.prodMapper);
	}

	@Override
	public void addProductFeatures(List<ProductFeatureDTO> features) {
		if (features == null || features.isEmpty()) {
			return;
		}
		InsertValuesStep4<ProductFeatureRecord, Long, Long, Long, String> insertInto = dlsContext.insertInto(
				PRODUCT_FEATURE, PRODUCT_FEATURE.ID, PRODUCT_FEATURE.PRODUCT, PRODUCT_FEATURE.FEATURE,
				PRODUCT_FEATURE.FEATURE_VALUE);

		for (ProductFeatureDTO prodFeature : features) {
			insertInto.values(prodFeature.getId(), prodFeature.getProduct(), prodFeature.getFeature().getId(),
					prodFeature.getFeatureValue());
		}
		insertInto.execute();
	}

	@Override
	public Map<Long, ProductDTO> getProductsByIds(Set<Long> ids) {
		Map<Long, ProductDTO> prodMap = dlsContext.select().from(PRODUCT).where(PRODUCT.ID.in(ids)).fetch()
				.intoMap(PRODUCT.ID, DTOMappers.prodMapper);
		return prodMap;
	}
}
