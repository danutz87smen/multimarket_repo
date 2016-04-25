package com.dan.dao.impl;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.Sequences;
import org.jooq.tables.Feature;
import org.jooq.tables.ProductFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.FeatureDTO;
import com.dan.model.ProductFeatureDTO;
import com.dan.util.DTOMappers;
import com.dao.intf.IFeatureDao;

@Repository
public class FeatureDao implements IFeatureDao {
	@Autowired
	private DSLContext dlsContext;

	private static final RecordMapper<Record, FeatureDTO> featureMapper = (Record record) -> new FeatureDTO(
			record.getValue(Feature.FEATURE.ID), record.getValue(Feature.FEATURE.NAME));

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#getFeatures()
	 */
	@Override
	public List<FeatureDTO> getFeatures() {
		List<FeatureDTO> features = dlsContext.fetch(Feature.FEATURE).map(featureMapper);
		return features;
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#getFeatureById(int)
	 */
	@Override
	public FeatureDTO getFeatureById(int id) {
		return dlsContext.fetchOne(Feature.FEATURE, Feature.FEATURE.ID.eq(id)).map(featureMapper);
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#updateFeature(com.dan.model.FeatureDTO)
	 */
	@Override
	public boolean updateFeature(FeatureDTO feature) {
		int updateRows = dlsContext.update(Feature.FEATURE).set(Feature.FEATURE.NAME, feature.getName())
				.where(Feature.FEATURE.ID.eq(feature.getId())).execute();
		return updateRows > 0;
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#deleteFeature(com.dan.model.FeatureDTO)
	 */
	@Override
	public boolean deleteFeature(FeatureDTO feature) {
		int deletedRows = dlsContext.delete(Feature.FEATURE).where(Feature.FEATURE.ID.eq(feature.getId())).execute();
		return deletedRows > 0;
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#deleteFeatureById(int)
	 */
	public void deleteFeatureById(int id) {
		dlsContext.delete(Feature.FEATURE).where(Feature.FEATURE.ID.eq(id)).execute();
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#createFeature(com.dan.model.FeatureDTO)
	 */
	@Override
	public FeatureDTO createFeature(FeatureDTO feature) {
		int id = dlsContext.nextval(Sequences.FEATURE_SEQ).intValue();
		dlsContext.insertInto(Feature.FEATURE, Feature.FEATURE.ID, Feature.FEATURE.NAME).values(id, feature.getName())
				.returning(Feature.FEATURE.ID).fetchOne();
		feature.setId(id);
		return feature;
	}

	/* (non-Javadoc)
	 * @see com.dao.intf.IFeatureDao#getFeaturesByProductId(int)
	 */
	@Override
	public List<FeatureDTO> getFeaturesByProductId(int productId) {
		List<FeatureDTO> features = dlsContext.select(Feature.FEATURE.ID, Feature.FEATURE.NAME)
				.from(ProductFeature.PRODUCT_FEATURE).join(Feature.FEATURE)
				.on(ProductFeature.PRODUCT_FEATURE.FEATURE.eq(Feature.FEATURE.ID))
				.where(ProductFeature.PRODUCT_FEATURE.PRODUCT.eq(productId)).fetch().map(featureMapper);
		return features;
	}
	
	@Override
	public List<ProductFeatureDTO> getProductFeatures(int productId) {
		return dlsContext.select().from(ProductFeature.PRODUCT_FEATURE.join(Feature.FEATURE).onKey())
				.where(ProductFeature.PRODUCT_FEATURE.PRODUCT.eq(productId)).fetch().map(DTOMappers.productFeaturesMapper);
	}
}
