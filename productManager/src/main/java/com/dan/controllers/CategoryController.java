package com.dan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.CategoryDTO;
import com.dan.services.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/categories/", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getAllcategories() {
		List<CategoryDTO> categories = categoryService.getAll();
		return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getcategoryById(@PathVariable int id) {
		CategoryDTO searchedcategory = null;
		try {
			searchedcategory = categoryService.getById(id);
		} catch (ModelNotFoundException e) {
			new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryDTO>(searchedcategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CategoryDTO> updatecategory(@PathVariable int id, @RequestBody CategoryDTO category) {
		CategoryDTO originalcategory = null;
		try {
			originalcategory = categoryService.getById(id);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
		}
		originalcategory.setName(category.getName());
		categoryService.update(originalcategory);
		return new ResponseEntity<CategoryDTO>(originalcategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CategoryDTO> deletecategory(@PathVariable int id) {
		try {
			categoryService.delete(id);
			return new ResponseEntity<CategoryDTO>(HttpStatus.NO_CONTENT);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/categories/", method = RequestMethod.POST)
	public ResponseEntity<CategoryDTO> createcategory(@RequestBody CategoryDTO category) {
		categoryService.create(category);
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
	}
}
