package com.dan.model;


public class ProductFeatureDTO {
		private int id;
		private int productId;
		private FeatureDTO feature;
		private String featureValue;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public FeatureDTO getFeature() {
			return feature;
		}

		public void setFeature(FeatureDTO feature) {
			this.feature = feature;
		}

		public int getProduct() {
			return productId;
		}

		public void setProduct(int product) {
			this.productId = product;
		}

		public String getFeatureValue() {
			return featureValue;
		}

		public void setFeatureValue(String featureValue) {
			this.featureValue = featureValue;
		}
}
