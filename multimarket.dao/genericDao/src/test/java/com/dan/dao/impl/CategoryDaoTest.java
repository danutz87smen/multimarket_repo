package com.dan.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dan.model.CategoryDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring_jooq.xml" })
public class CategoryDaoTest {

	@Autowired
	private CategoryDao dao;
	private CategoryDTO initialCategory = new CategoryDTO("bere");
	private static final String NEW_NAME = "new Name";

	@Test
	public void testCreate() {
		CategoryDTO productDTO = dao.createCategory(initialCategory);
		CategoryDTO productDB = dao.getCategoryById(productDTO.getId());
		Assert.assertEquals(productDTO, productDB);
	}

	@Test
	public void testUpdate() {
		CategoryDTO productDTO = dao.createCategory(initialCategory);
		productDTO.setName(NEW_NAME);
		Assert.assertTrue(dao.updateCategory(productDTO));
		productDTO = dao.getCategoryById(productDTO.getId());
		Assert.assertEquals(NEW_NAME, productDTO.getName());
	}

	@Test
	public void testDelete() {
		CategoryDTO productDTO = dao.createCategory(initialCategory);
		Assert.assertTrue(dao.deleteCategory(productDTO));

		productDTO = dao.getCategoryById(productDTO.getId());
		Assert.assertNull(productDTO);
	}

	@Test
	public void testDeleteById() {
		CategoryDTO productDTO = dao.createCategory(initialCategory);
		dao.deleteCategoryById(productDTO.getId());

		productDTO = dao.getCategoryById(productDTO.getId());
		Assert.assertNull(productDTO);
	}

	@Test
	public void testGetAll() {
		initialCategory.setParentCategory(dao.createCategory(initialCategory).getParentCategory());
		dao.createCategory(initialCategory);
		Assert.assertTrue(!dao.getCategories().isEmpty());
	}
}
