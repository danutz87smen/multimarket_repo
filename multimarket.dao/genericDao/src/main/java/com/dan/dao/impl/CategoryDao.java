package com.dan.dao.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Sequences;
import org.jooq.tables.Category;
import org.jooq.tables.records.CategoryRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.CategoryDTO;
import com.dao.intf.ICategoryDao;

@Repository
public class CategoryDao implements ICategoryDao {
	@Autowired
	private DSLContext dlsContext;
	private static final RecordMapper<Record, CategoryDTO> catMapper = (Record record) -> new CategoryDTO(
			record.getValue(Category.CATEGORY.ID), record.getValue(Category.CATEGORY.NAME),
			record.getValue(Category.CATEGORY.PARENT_CATEGORY));

	public List<CategoryDTO> getCategories() {
		return dlsContext.fetch(Category.CATEGORY).map(catMapper);
	}

	public CategoryDTO getCategoryById(long id) {
		 CategoryRecord fetchOne = dlsContext.fetchOne(Category.CATEGORY, Category.CATEGORY.ID.eq(id));
		if(fetchOne == null){
			return null;
		}
		 return fetchOne.map(catMapper);
	}

	public boolean updateCategory(CategoryDTO category) {
		int updateRows = dlsContext.update(Category.CATEGORY).set(Category.CATEGORY.NAME, category.getName())
				.where(Category.CATEGORY.ID.eq(category.getId())).execute();
		return updateRows > 0;
	}

	public boolean deleteCategory(CategoryDTO category) {
		int deletedRows = dlsContext.delete(Category.CATEGORY).where(Category.CATEGORY.ID.eq(category.getId()))
				.execute();
		return deletedRows > 0;
	}

	public void deleteCategoryById(long id) {
		dlsContext.delete(Category.CATEGORY).where(Category.CATEGORY.ID.eq(id)).execute();
	}

	@Override
	public CategoryDTO createCategory(CategoryDTO category) {
		long id = dlsContext.nextval(Sequences.CATEGORY_SEQ).intValue();
		dlsContext
				.insertInto(Category.CATEGORY, Category.CATEGORY.ID, Category.CATEGORY.NAME,
						Category.CATEGORY.PARENT_CATEGORY)
				.values(id, category.getName(), category.getParentCategory()).returning(Category.CATEGORY.ID)
				.fetchOne();
		category.setId(id);
		return category;
	}
}
