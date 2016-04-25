package com.dan.util;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.tables.Feature;
import org.jooq.tables.Product;
import org.jooq.tables.ProductFeature;

import com.dan.model.FeatureDTO;
import com.dan.model.ProductDTO;
import com.dan.model.ProductFeatureDTO;

public class DTOMappers {
	public static final RecordMapper<Record, ProductDTO> prodMapper = (Record record) -> new ProductDTO(
			record.getValue(Product.PRODUCT.ID),
			record.getValue(Product.PRODUCT.NAME),
			record.getValue(Product.PRODUCT.DESCRIPTION), 
			record.getValue(Product.PRODUCT.CATGORY_ID),
			record.getValue(Product.PRODUCT.STOCK));

	public static final RecordMapper<Record, ProductFeatureDTO> productFeaturesMapper = (Record record) -> {
		ProductFeatureDTO productFeature = new ProductFeatureDTO();
		productFeature.setId(record.getValue(ProductFeature.PRODUCT_FEATURE.ID));
		productFeature.setFeatureValue(record.getValue(ProductFeature.PRODUCT_FEATURE.FEATURE_VALUE));
		productFeature.setProduct(record.getValue(ProductFeature.PRODUCT_FEATURE.PRODUCT));
		productFeature
				.setFeature(new FeatureDTO(record.getValue(Feature.FEATURE.ID), record.getValue(Feature.FEATURE.NAME)));
		return productFeature;
	};
}
