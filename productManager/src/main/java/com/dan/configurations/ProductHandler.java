package com.dan.configurations;

import java.util.ArrayList;
import java.util.List;

import com.dan.model.ProductDTO;

public class ProductHandler {

	public List<ProductDTO> handleMessage(List<Integer> list) {
		System.out.println(list);
		ProductDTO p1 =new ProductDTO("name", "description", 11, 12);
		List<ProductDTO> prodList = new ArrayList<>();
		prodList.add(p1);
		
		return prodList;
	}
}
