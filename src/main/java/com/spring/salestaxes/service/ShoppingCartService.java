package com.spring.salestaxes.service;

import java.util.List;
import com.spring.salestaxes.model.ShoppingCartModel;

public interface ShoppingCartService {
	
	List<ShoppingCartModel> findAll();
	ShoppingCartModel findById(long id);
	Double getTotal(List<ShoppingCartModel> listaItens);
	Double getSalesTaxes(List<ShoppingCartModel> listaItens);
}
