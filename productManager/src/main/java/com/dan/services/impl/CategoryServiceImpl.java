package com.dan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.CategoryDTO;
import com.dan.services.CategoryService;
import com.dao.intf.ICategoryDao;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private ICategoryDao dao;

	@Override
	public List<CategoryDTO> getAll() {
		return dao.getCategories();
	}

	@Override
	public CategoryDTO getById(int id) throws ModelNotFoundException {
		return dao.getCategoryById(id);
	}

	@Override
	public void update(CategoryDTO category) {
		dao.updateCategory(category);

	}

	@Override
	public CategoryDTO create(CategoryDTO category) {
		return dao.createCategory(category);
	}

	@Override
	public void delete(int id) throws ModelNotFoundException {
		dao.deleteCategoryById(id);
	}
}
