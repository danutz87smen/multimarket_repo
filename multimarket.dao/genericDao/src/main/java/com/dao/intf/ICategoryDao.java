package com.dao.intf;

import java.util.List;

import com.dan.model.CategoryDTO;

/**
 * Category DAO.
 * 
 * @author Deea
 *
 */
public interface ICategoryDao {

	/**
	 * @return list with all categories.
	 */
	List<CategoryDTO> getCategories();

	/**
	 * @param id
	 *            of searched category
	 * @return searched category
	 */
	CategoryDTO getCategoryById(long id);

	/**
	 * Update a specific category
	 * 
	 * @param category
	 *            to be updated
	 * @return
	 */
	boolean updateCategory(CategoryDTO category);

	/**
	 * Create a new category.
	 * 
	 * @param category
	 * @return
	 */
	CategoryDTO createCategory(CategoryDTO category);

	/**
	 * Delete a category from DB.
	 * 
	 * @param category
	 * @return
	 */
	boolean deleteCategory(CategoryDTO category);

	/**
	 * Delete a category from DB by Id.
	 * 
	 * @param id
	 */
	void deleteCategoryById(long id);
}
