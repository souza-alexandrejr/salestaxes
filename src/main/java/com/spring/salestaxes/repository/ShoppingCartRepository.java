package com.spring.salestaxes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.salestaxes.model.ShoppingCartModel;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel, Long>{

}
