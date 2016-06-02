package com.dan.services;

import java.util.Map;
import java.util.Set;

import com.dan.model.ProductDTO;

public interface AmqpProductService {

	 Map<Long, ProductDTO> getProductsByIds(Set<Long> prodIdSet);
}
