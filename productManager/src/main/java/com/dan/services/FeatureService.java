package com.dan.services;

import java.util.List;

import com.dan.model.FeatureDTO;
import com.dan.model.ProductFeatureDTO;

public interface FeatureService extends ModelService<FeatureDTO> {
	List<ProductFeatureDTO> getProductFeatures(int productId);
}
