package com.dan.services;

import java.util.List;

import com.dan.exceptions.ModelNotFoundException;

public interface ModelService<T> {

	List<T> getAll();

	T getById(int id) throws ModelNotFoundException;

	void update(T t);

	T create(T t);

	void delete(int id) throws ModelNotFoundException;
}
