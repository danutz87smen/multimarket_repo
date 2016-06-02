package com.dan.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dan.model.ProductDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring_jooq.xml" })
public class ProductDaoTest {

	@Autowired
	private ProductDao dao;
	private ProductDTO initialProduct = new ProductDTO("name", "description", null, 12, 22);
	private static final String NEW_NAME = "new Name";

	@Test
	public void testCreate() {
		ProductDTO productDTO = dao.createProduct(initialProduct);
		ProductDTO productDB = dao.getProductById(productDTO.getId());
		Assert.assertEquals(productDTO, productDB);
	}

	@Test
	public void testUpdate() {
		ProductDTO productDTO = dao.createProduct(initialProduct);
		productDTO.setName(NEW_NAME);
		Assert.assertTrue(dao.updateProduct(productDTO));
		productDTO = dao.getProductById(productDTO.getId());
		Assert.assertEquals(NEW_NAME, productDTO.getName());
	}

	@Test
	public void testDelete() {
		ProductDTO productDTO = dao.createProduct(initialProduct);
		Assert.assertTrue(dao.deleteProduct(productDTO));

		productDTO = dao.getProductById(productDTO.getId());
		Assert.assertNull(productDTO);
	}

	@Test
	public void testDeleteById() {
		ProductDTO productDTO = dao.createProduct(initialProduct);
		dao.deleteProductById(productDTO.getId());

		productDTO = dao.getProductById(productDTO.getId());
		Assert.assertNull(productDTO);
	}
	
	@Test
	public void testGetAll() {
		dao.createProduct(initialProduct);
		dao.createProduct(initialProduct);
		Assert.assertTrue(!dao.getProducts().isEmpty());
	}
}
