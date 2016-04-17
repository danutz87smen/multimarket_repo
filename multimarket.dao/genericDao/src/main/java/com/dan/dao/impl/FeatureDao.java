package com.dan.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Sequences;
import org.jooq.tables.Feature;
import org.jooq.tables.ProductFeature;
import org.jooq.tables.records.FeatureRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dan.model.FeatureDTO;
import com.dao.intf.IFeatureDao;

@Repository
public class FeatureDao implements IFeatureDao {
	@Autowired
	private DSLContext dlsContext;

	public List<FeatureDTO> getFeatures() {
		List<FeatureDTO> features = dlsContext.fetch(Feature.FEATURE)
				.map((Record record) -> new FeatureDTO(record.getValue(Feature.FEATURE.ID),
						record.getValue(Feature.FEATURE.NAME)));
		return features;
	}

	public FeatureDTO getFeatureById(int id) {
		FeatureRecord dbCat = dlsContext.fetchOne(Feature.FEATURE, Feature.FEATURE.ID.eq(id));
		return convertToFeature(dbCat);
	}

	private FeatureDTO convertToFeature(FeatureRecord dbFeat) {
		return new FeatureDTO(dbFeat.getId(), dbFeat.getName());
	}

	public boolean updateFeature(FeatureDTO feature) {
		int updateRows = dlsContext.update(Feature.FEATURE).set(Feature.FEATURE.NAME, feature.getName())
				.where(Feature.FEATURE.ID.eq(feature.getId())).execute();
		return updateRows > 0;
	}

	public boolean deleteFeature(FeatureDTO feature) {
		int deletedRows = dlsContext.delete(Feature.FEATURE).where(Feature.FEATURE.ID.eq(feature.getId())).execute();
		return deletedRows > 0;
	}

	public void deleteFeatureById(int id) {
		dlsContext.delete(Feature.FEATURE).where(Feature.FEATURE.ID.eq(id)).execute();
	}

	@Override
	public FeatureDTO createFeature(FeatureDTO feature) {
		int id = dlsContext.nextval(Sequences.FEATURE_SEQ).intValue();
		dlsContext.insertInto(Feature.FEATURE, Feature.FEATURE.ID, Feature.FEATURE.NAME).values(id, feature.getName())
				.returning(Feature.FEATURE.ID).fetchOne();
		feature.setId(id);
		return feature;
	}

	@Override
	public List<FeatureDTO> getFeaturesByProductId(int productId) {
		List<FeatureDTO> features = dlsContext.select(Feature.FEATURE.ID, Feature.FEATURE.NAME)
				.from(ProductFeature.PRODUCT_FEATURE).join(Feature.FEATURE)
				.on(ProductFeature.PRODUCT_FEATURE.FEATURE.eq(Feature.FEATURE.ID))
				.where(ProductFeature.PRODUCT_FEATURE.PRODUCT.eq(productId)).fetch()
				.map((Record record) -> new FeatureDTO(record.getValue(Feature.FEATURE.ID),
						record.getValue(Feature.FEATURE.NAME)));
		return features;
	}
}
