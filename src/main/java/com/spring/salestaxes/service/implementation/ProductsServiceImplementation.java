package com.spring.salestaxes.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.salestaxes.model.ProductsModel;
import com.spring.salestaxes.repository.ProductsRepository;
import com.spring.salestaxes.service.ProductsService;

@Service
public class ProductsServiceImplementation implements ProductsService {
	
	@Autowired
	ProductsRepository salestaxesRepository;
	
	@Override
	public List<ProductsModel> findAll() {
		return salestaxesRepository.findAll();
	}
	
	@Override
	public ProductsModel findById(long id) {
		return salestaxesRepository.findById(id).get();
	}
	
	
	@Override
	public ProductsModel save(ProductsModel product) {
		return salestaxesRepository.save(product);
	}
	
}
