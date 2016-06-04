package com.dan.model;

import java.io.Serializable;

public class ProductFeatureDTO implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private long id;
		private long productId;
		private FeatureDTO feature;
		private String featureValue;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public FeatureDTO getFeature() {
			return feature;
		}

		public void setFeature(FeatureDTO feature) {
			this.feature = feature;
		}

		public long getProduct() {
			return productId;
		}

		public void setProduct(long product) {
			this.productId = product;
		}

		public String getFeatureValue() {
			return featureValue;
		}

		public void setFeatureValue(String featureValue) {
			this.featureValue = featureValue;
		}
}
