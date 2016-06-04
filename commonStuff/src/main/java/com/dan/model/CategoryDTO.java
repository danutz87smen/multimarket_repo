package com.dan.model;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Long parentCategory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Long parentCategory) {
		this.parentCategory = parentCategory;
	}

	public CategoryDTO(String name, Long parentCategory) {
		super();
		this.name = name;
		this.parentCategory = parentCategory;
	}

	public CategoryDTO(long id, String name, Long parentCategory) {
		super();
		this.id = id;
		this.name = name;
		this.parentCategory = parentCategory;
	}

	public CategoryDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoryDTO(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentCategory == null) ? 0 : parentCategory.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentCategory == null) {
			if (other.parentCategory != null)
				return false;
		} else if (!parentCategory.equals(other.parentCategory))
			return false;
		return true;
	}

}
