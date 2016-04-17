package com.dao.intf;

import java.util.List;

import com.dan.model.CategoryDTO;


public interface ICategoryDao {
	
	List<CategoryDTO> getCategories();

	CategoryDTO getCategoryById(int id);

	boolean updateCategory(CategoryDTO category);

	CategoryDTO createCategory(CategoryDTO category);

	boolean deleteCategory(CategoryDTO category);

	void deleteCategoryById(int id);
}
