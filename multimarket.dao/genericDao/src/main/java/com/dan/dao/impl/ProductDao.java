package com.dan.dao.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Sequences;
import org.jooq.tables.Product;
import org.jooq.tables.records.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.ProductDTO;
import com.dao.intf.IProductDao;

@Repository
public class ProductDao implements IProductDao {
	@Autowired
	private DSLContext dlsContext;

//	private static final RecordMapper<ProductRecord, ProductDTO> productMapper = (
//			ProductRecord record) -> new ProductDTO(record.getId(), record.getName(), record.getDescription(),
//					record.getCatgoryId(), record.getStock());

	private static final RecordMapper<Record, ProductDTO> prodMapper = (Record record) -> new ProductDTO(
			record.getValue(Product.PRODUCT.ID), record.getValue(Product.PRODUCT.NAME),
			record.getValue(Product.PRODUCT.DESCRIPTION), record.getValue(Product.PRODUCT.CATGORY_ID),
			record.getValue(Product.PRODUCT.STOCK));

	public List<ProductDTO> getProducts() {
		List<ProductDTO> products = dlsContext.fetch(Product.PRODUCT).map(prodMapper);
		return products;
	}

	public ProductDTO getProductById(int id) {
		return dlsContext.fetchOne(Product.PRODUCT, Product.PRODUCT.ID.eq(id)).map(prodMapper);
	}
//
//	private ProductDTO convertToProduct(ProductRecord dbProd) {
//		return new ProductDTO(dbProd.getId(), dbProd.getName(), dbProd.getDescription(), dbProd.getCatgoryId(),
//				dbProd.getStock());
//	}

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
}
