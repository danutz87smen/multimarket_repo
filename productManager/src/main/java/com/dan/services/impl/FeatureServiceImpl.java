package com.dan.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dan.exceptions.ModelNotFoundException;
import com.dan.model.FeatureDTO;
import com.dan.model.ProductFeatureDTO;
import com.dan.services.FeatureService;
import com.dao.intf.IFeatureDao;

@Service
@Transactional
public class FeatureServiceImpl implements FeatureService {
	@Autowired
	private IFeatureDao dao;

	@Override
	public List<FeatureDTO> getAll() {
		return dao.getFeatures();
	}

	@Override
	public FeatureDTO getById(int id) throws ModelNotFoundException {
		return dao.getFeatureById(id);
	}

	@Override
	public void update(FeatureDTO feature) {
		dao.updateFeature(feature);

	}

	@Override
	public FeatureDTO create(FeatureDTO feature) {
		return dao.createFeature(feature);
	}

	@Override
	public void delete(int id) throws ModelNotFoundException {
		dao.deleteFeatureById(id);
	}

	@Override
	public List<ProductFeatureDTO> getProductFeatures(int productId) {
		return dao.getProductFeatures(productId);
	}
}
