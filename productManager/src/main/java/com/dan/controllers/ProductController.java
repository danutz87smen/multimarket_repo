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
import com.dan.model.ProductDTO;
import com.dan.services.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAll();
		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
		ProductDTO searchedProduct = null;
		try {
			searchedProduct = productService.getById(id);
		} catch (ModelNotFoundException e) {
			new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDTO>(searchedProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO product) {
		ProductDTO originalProduct = null;
		try {
			originalProduct = productService.getById(id);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
		originalProduct.setName(product.getName());
		originalProduct.setDescription(product.getDescription());
		originalProduct.setStock(product.getStock());

		productService.update(originalProduct);
		return new ResponseEntity<ProductDTO>(originalProduct, HttpStatus.OK);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProductDTO> deleteProduct(@PathVariable int id) {
		try {
			productService.delete(id);
			return new ResponseEntity<ProductDTO>(HttpStatus.NO_CONTENT);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/products/", method = RequestMethod.POST)
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
		productService.create(product);
		return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
	}
}
