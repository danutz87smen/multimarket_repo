package com.dan.model;

public class ProductDTO {
	private int id;
	private String name;
	private String description;
	private int stock;
	// private List<ProductFeature> features = new ArrayList<>();
	private Integer categoryId;

	public ProductDTO() {
		super();
	}

	public ProductDTO(int id, String name, String description, Integer cat, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.categoryId = cat;
		this.stock = stock;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDTO other = (ProductDTO) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public class ProductFeature {
		private int id;
		private FeatureDTO feature;
		private ProductDTO product;
		private String featureValue;

		protected int getId() {
			return id;
		}

		protected void setId(int id) {
			this.id = id;
		}

		protected FeatureDTO getFeature() {
			return feature;
		}

		protected void setFeature(FeatureDTO feature) {
			this.feature = feature;
		}

		protected ProductDTO getProduct() {
			return product;
		}

		protected void setProduct(ProductDTO product) {
			this.product = product;
		}

		protected String getFeatureValue() {
			return featureValue;
		}

		protected void setFeatureValue(String featureValue) {
			this.featureValue = featureValue;
		}

		public ProductFeature() {
			super();
		}

		public ProductFeature(int id, FeatureDTO feature, ProductDTO product, String featureValue) {
			super();
			this.id = id;
			this.feature = feature;
			this.product = product;
			this.featureValue = featureValue;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ProductFeature other = (ProductFeature) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (id != other.id)
				return false;
			return true;
		}

		private ProductDTO getOuterType() {
			return ProductDTO.this;
		}
	}
}
