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
import com.dan.model.FeatureDTO;
import com.dan.model.ProductFeatureDTO;
import com.dan.services.FeatureService;

@RestController
public class FeatureController {
	@Autowired
	private FeatureService featureService;
	
	@RequestMapping(value = "/products/{id}/features/", method = RequestMethod.GET)
	public ResponseEntity<List<ProductFeatureDTO>> getProductFeatures(@PathVariable int id) {
		List<ProductFeatureDTO> features = null;
		features = featureService.getProductFeatures(id);
		return new ResponseEntity<List<ProductFeatureDTO>>(features, HttpStatus.OK);
	}

	@RequestMapping(value = "/features/", method = RequestMethod.GET)
	public ResponseEntity<List<FeatureDTO>> getAllFeatures() {
		List<FeatureDTO> features = featureService.getAll();
		return new ResponseEntity<List<FeatureDTO>>(features, HttpStatus.OK);
	}

	@RequestMapping(value = "/features/{id}", method = RequestMethod.GET)
	public ResponseEntity<FeatureDTO> getFeatureById(@PathVariable int id) {
		FeatureDTO searchedFeature = null;
		try {
			searchedFeature = featureService.getById(id);
		} catch (ModelNotFoundException e) {
			new ResponseEntity<FeatureDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FeatureDTO>(searchedFeature, HttpStatus.OK);
	}

	@RequestMapping(value = "/features/{id}", method = RequestMethod.PUT)
	public ResponseEntity<FeatureDTO> updateFeature(@PathVariable int id, @RequestBody FeatureDTO feature) {
		FeatureDTO originalFeature = null;
		try {
			originalFeature = featureService.getById(id);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<FeatureDTO>(HttpStatus.NOT_FOUND);
		}
		originalFeature.setName(feature.getName());
		featureService.update(originalFeature);
		return new ResponseEntity<FeatureDTO>(originalFeature, HttpStatus.OK);
	}

	@RequestMapping(value = "/features/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<FeatureDTO> deleteFeature(@PathVariable int id) {
		try {
			featureService.delete(id);
			return new ResponseEntity<FeatureDTO>(HttpStatus.NO_CONTENT);
		} catch (ModelNotFoundException e) {
			return new ResponseEntity<FeatureDTO>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/features/", method = RequestMethod.POST)
	public ResponseEntity<FeatureDTO> createFeature(@RequestBody FeatureDTO feature) {
		featureService.create(feature);
		return new ResponseEntity<FeatureDTO>(feature, HttpStatus.CREATED);
	}
}
