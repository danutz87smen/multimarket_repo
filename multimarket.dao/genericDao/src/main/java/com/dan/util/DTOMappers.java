package com.dan.util;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.tables.Feature;
import org.jooq.tables.Product;
import org.jooq.tables.ProductFeature;

import com.dan.model.FeatureDTO;
import com.dan.model.ProductDTO;
import com.dan.model.ProductFeatureDTO;

//problematic in multithread --> trebuie schimbat
public class DTOMappers {
	public static final RecordMapper<Record, ProductDTO> prodMapper = (Record record) -> new ProductDTO(
			record.getValue(Product.PRODUCT.ID), record.getValue(Product.PRODUCT.NAME),
			record.getValue(Product.PRODUCT.DESCRIPTION), record.getValue(Product.PRODUCT.CATGORY_ID),
			record.getValue(Product.PRODUCT.STOCK), record.getValue(Product.PRODUCT.PRICE),
			record.getValue(Product.PRODUCT.PHOTOS));

	public static final RecordMapper<Record, ProductFeatureDTO> productFeaturesMapper = (Record record) -> {
		ProductFeatureDTO productFeature = new ProductFeatureDTO();
		productFeature.setId(record.getValue(ProductFeature.PRODUCT_FEATURE.ID));
		productFeature.setFeatureValue(record.getValue(ProductFeature.PRODUCT_FEATURE.FEATURE_VALUE));
		productFeature.setProduct(record.getValue(ProductFeature.PRODUCT_FEATURE.PRODUCT));
		productFeature
				.setFeature(new FeatureDTO(record.getValue(Feature.FEATURE.ID), record.getValue(Feature.FEATURE.NAME)));
		return productFeature;
	};

//	public static final RecordMapper<Record, OrderListDTO> orderMapper = (Record record) -> {
//		OrderListDTO orderList = new OrderListDTO();
//		orderList.setId(record.getValue(OrderList.ORDER_LIST.ID));
//		orderList.setOrderDate(record.getValue(OrderList.ORDER_LIST.ORDER_DATE));
//		orderList.setAccountId(record.getValue(OrderList.ORDER_LIST.ACCOUNT_ID));
//		return orderList;
//	};
//	
//	public static final RecordMapper<Record, OrderItemDTO> orderItemMapper = (Record record) -> {
//		OrderItemDTO item = new OrderItemDTO();
//		item.setId(record.getValue(OrderItem.ORDER_ITEM.ID));
//		item.setQuantity(record.getValue(OrderItem.ORDER_ITEM.QUANTITY));
//		item.setTotal(record.getValue(OrderItem.ORDER_ITEM.TOTAL));
//		item.settVAValue(record.getValue(OrderItem.ORDER_ITEM.TVAVALUE));
//		ProductDTO product = new ProductDTO(record.getValue(Product.PRODUCT.ID),
//				record.getValue(Product.PRODUCT.NAME),record.getValue(Product.PRODUCT.DESCRIPTION),
//				record.getValue(Product.PRODUCT.CATGORY_ID), record.getValue(Product.PRODUCT.STOCK));
//		item.setProduct(product);
//		return item;
//	};

}
