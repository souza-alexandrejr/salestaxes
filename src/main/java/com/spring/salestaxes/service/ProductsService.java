package com.spring.salestaxes.service;

import java.util.List;

import com.spring.salestaxes.model.ProductsModel;

public interface ProductsService {
	
	List<ProductsModel> findAll();
	ProductsModel findById(long id);
	ProductsModel save(ProductsModel product);

}
