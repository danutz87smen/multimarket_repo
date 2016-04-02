package com.dan.services;

import java.util.List;

import com.dan.exceptions.ModelNotFoundException;

public interface ModelService<T> {

	List<T> getAll();

	T getById(long id) throws ModelNotFoundException;

	void update(T t);

	T insert(T t);

	void delete(long id) throws ModelNotFoundException;
}
