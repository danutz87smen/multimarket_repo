//package com.dan.dao.impl;
//
//import java.util.List;
//
//import org.jooq.DSLContext;
//import org.jooq.InsertValuesStep4;
//import org.jooq.Sequences;
//import org.jooq.tables.Category;
//import org.jooq.tables.Feature;
//import org.jooq.tables.Order;
//import org.jooq.tables.OrderFeature;
//import org.jooq.tables.OrderItem;
//import org.jooq.tables.OrderList;
//import org.jooq.tables.Product;
//import org.jooq.tables.records.OrderFeatureRecord;
//import org.jooq.tables.records.OrderListRecord;
//import org.jooq.tables.records.OrderRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.dan.model.OrderListDTO;
//import com.dan.model.OrderFeatureDTO;
//import com.dan.model.OrderItemDTO;
//import com.dan.util.DTOMappers;
//import com.dao.intf.IOrderDao;
//
//@Repository
//public class OrderDao implements IOrderDao {
//	@Autowired
//	private DSLContext dlsContext;
//
//	//get all without items
//	public List<OrderListDTO> getOrders() {
//		return dlsContext.fetch(OrderList.ORDER_LIST).map(DTOMappers.orderMapper);
//	}
//
//	//get one with items
//	public OrderListDTO getOrderById(int id) {
//		 OrderListDTO order = dlsContext.fetchOne(OrderList.ORDER_LIST, OrderList.ORDER_LIST.ID.eq(id)).map(DTOMappers.orderMapper);
//		
//		List<OrderItemDTO> items = dlsContext.select().from(OrderItem.ORDER_ITEM.join(Product.PRODUCT).onKey())
//		.where(OrderItem.ORDER_ITEM.ORDER_ID.eq(id)).fetch().map(DTOMappers.orderItemMapper);
//		
//		order.getItems().addAll(items);
//		return order;
//	}
//
//	public boolean updateOrder(OrderListDTO Order) {
//		int updateRows = dlsContext.update(OrderList.ORDER_LIST).set(OrderList.ORDER_LIST.NAME, Order.getName())
//				.set(OrderList.ORDER_LIST.DESCRIPTION, Order.getDescription())
//				.set(OrderList.ORDER_LIST.CATGORY_ID, Order.getCategoryId()).set(OrderList.ORDER_LIST.STOCK, Order.getStock())
//				.where(OrderList.ORDER_LIST.ID.eq(Order.getId())).execute();
//		return updateRows > 0;
//	}
//
//	public boolean deleteOrder(OrderListDTO Order) {
//		int deletedRows = dlsContext.delete(OrderList.ORDER_LIST).where(OrderList.ORDER_LIST.ID.eq(Order.getId())).execute();
//		return deletedRows > 0;
//	}
//
//	public void deleteOrderById(int id) {
//		dlsContext.delete(OrderList.ORDER_LIST).where(OrderList.ORDER_LIST.ID.eq(id)).execute();
//	}
//
//	@Override
//	public OrderListDTO createOrder(OrderListDTO Order) {
//		int id = dlsContext.nextval(Sequences.Order_SEQ).intValue();
//		dlsContext
//				.insertInto(OrderList.ORDER_LIST, OrderList.ORDER_LIST.ID, OrderList.ORDER_LIST.NAME, OrderList.ORDER_LIST.DESCRIPTION,
//						OrderList.ORDER_LIST.CATGORY_ID, OrderList.ORDER_LIST.STOCK)
//				.values(id, Order.getName(), Order.getDescription(), Order.getCategoryId(), Order.getStock())
//				.returning(OrderList.ORDER_LIST.ID).fetchOne();
//		Order.setId(id);
//		return Order;
//	}
//
//	@Override
//	public List<OrderListDTO> getOrderByCategoryId(int categoryId) {
//		return dlsContext.select().from(Category.CATEGORY.join(OrderList.ORDER_LIST).onKey())
//				.where(Category.CATEGORY.ID.eq(categoryId)).fetch().map(DTOMappers.prodMapper);
//	}
//
//	@Override
//	public void addOrderFeatures(List<OrderFeatureDTO> features) {
//		if (features == null || features.isEmpty()) {
//			return;
//		}
//		InsertValuesStep4<OrderFeatureRecord, Integer, Integer, Integer, String> insertInto = dlsContext.insertInto(OrderFeature.Order_FEATURE, OrderFeature.Order_FEATURE.ID,
//				OrderFeature.Order_FEATURE.Order, OrderFeature.Order_FEATURE.FEATURE,
//				OrderFeature.Order_FEATURE.FEATURE_VALUE);
//		
//		for(OrderFeatureDTO prodFeature: features){
//			insertInto.values(prodFeature.getId(), prodFeature.getOrder(), prodFeature.getFeature().getId(), prodFeature.getFeatureValue());
//		}
//		insertInto.execute();
//	}
//}
