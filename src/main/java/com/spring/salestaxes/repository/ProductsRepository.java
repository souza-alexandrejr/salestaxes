package com.spring.salestaxes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.salestaxes.model.ProductsModel;

public interface ProductsRepository extends JpaRepository<ProductsModel, Long>{
	

}
