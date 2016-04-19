package com.dan.model;

public class CategoryDTO {
	private int id;
	private String name;
	private CategoryDTO parentCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoryDTO getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CategoryDTO parentCategory) {
		this.parentCategory = parentCategory;
	}

	public CategoryDTO(String name, CategoryDTO parentCategory) {
		super();
		this.name = name;
		this.parentCategory = parentCategory;
	}

	public CategoryDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
