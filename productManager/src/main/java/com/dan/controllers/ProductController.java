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
import com.dan.model.Product;
import com.dan.services.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product searchedProduct = null;
		try {
			searchedProduct = productService.getById(id);
		} catch (ModelNotFoundException e) {
			new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(searchedProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
		Product originalProduct;
		try {
			originalProduct = productService.getById(id);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		originalProduct.setName(product.getName());
		originalProduct.setDescription(product.getDescription());
		originalProduct.setStock(product.getStock());

		productService.update(originalProduct);
		return new ResponseEntity<Product>(originalProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
		try {
			productService.delete(id);
			return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
}
