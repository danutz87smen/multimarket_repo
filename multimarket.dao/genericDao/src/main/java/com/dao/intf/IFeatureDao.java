package com.dao.intf;

import java.util.List;

import com.dan.model.FeatureDTO;
import com.dan.model.ProductFeatureDTO;

/**
 * Feature DAO.
 * 
 * @author Deea
 *
 */
public interface IFeatureDao {

	/**
	 * @return all feature from DB
	 */
	List<FeatureDTO> getFeatures();

	/**
	 * @param id
	 *            of searched feature
	 * @return searched feature
	 */
	FeatureDTO getFeatureById(int id);

	/**
	 * Update an existing feature.
	 * 
	 * @param feature
	 *            feature to be modified
	 * @return true if it was modified
	 */
	boolean updateFeature(FeatureDTO feature);

	/**
	 * Create a new feature in DB.
	 * 
	 * @param feature
	 *            to be stored
	 * @return stored feature
	 */
	FeatureDTO createFeature(FeatureDTO feature);

	/**
	 * Delete a feature from DB.
	 * 
	 * @param feature
	 *            to be deleted
	 * @return true if feature is deleted
	 */
	boolean deleteFeature(FeatureDTO feature);

	/**
	 * Delete feature by id.
	 * 
	 * @param id
	 */
	void deleteFeatureById(int id);

	/**
	 * Get a list of features that belongs to given product id.
	 * 
	 * @param productId
	 * @return
	 */
	List<FeatureDTO> getFeaturesByProductId(int productId);
	
	/**
	 * Get features for given product id.
	 * @param productId
	 * @return
	 */
	List<ProductFeatureDTO> getProductFeatures(int productId);
}
