package com.dan.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dan.model.ProductDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring_jooq.xml"})
public class CategoryDaoTest {
	
	@Autowired
	private ProductDao dao;
	
	@Test
	public void testCreate(){
		dao.createProduct(new ProductDTO("name", "description", null, 12));
		Assert.assertTrue(true);
	}
}
