package com.dao.intf;

import java.util.List;

import com.dan.model.FeatureDTO;


public interface IFeatureDao {
	
	List<FeatureDTO> getFeatures();

	FeatureDTO getFeatureById(int id);

	boolean updateFeature(FeatureDTO feature);

	FeatureDTO createFeature(FeatureDTO feature);

	boolean deleteFeature(FeatureDTO feature);

	void deleteFeatureById(int id);
	
	List<FeatureDTO>getFeaturesByProductId(int productId);
}
