package com.spring.salestaxes.service.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.salestaxes.model.ProductsModel;
import com.spring.salestaxes.model.ShoppingCartModel;
import com.spring.salestaxes.repository.ShoppingCartRepository;
import com.spring.salestaxes.service.ProductsService;
import com.spring.salestaxes.service.ShoppingCartService;

@Service
public class ShoppingCartImplementation implements ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	ProductsService productsService;
	
	@Override
	public List<ShoppingCartModel> findAll() {
		return shoppingCartRepository.findAll();
	}

	@Override
	public ShoppingCartModel findById(long id) {
		return shoppingCartRepository.findById(id).get();
	}

	@Override
	public Double getTotal(List<ShoppingCartModel> listaItens) {
		Double valorTotal = 0.00;
		
		for (ShoppingCartModel item : listaItens) {
			valorTotal += item.getValorCompra();
		}
		
		return valorTotal;
	}

	@Override
	public Double getSalesTaxes(List<ShoppingCartModel> listaItens) {
		Double salesTaxes = 0.00;
		
		for (ShoppingCartModel item : listaItens) {
			ProductsModel product = productsService.findById(item.getIdProduto());
			int vQtdProduto = item.getQtdProduto();
			
			if (product.getImported() == 1) {
				if (product.getCategory().equalsIgnoreCase("Books") || 
					product.getCategory().equalsIgnoreCase("Food") ||
					product.getCategory().equalsIgnoreCase("Medical Products")) {
					salesTaxes += (double) Math.ceil((0.05 * product.getPrice() * vQtdProduto) * 20) / 20;
				} else {
					salesTaxes += (double) Math.ceil((0.15 * product.getPrice() * vQtdProduto) * 20) / 20;
				}
			} else {
				if (product.getCategory().equalsIgnoreCase("Books") || 
					product.getCategory().equalsIgnoreCase("Food") ||
					product.getCategory().equalsIgnoreCase("Medical Products")) {
					salesTaxes += 0;
				} else {
					salesTaxes += (double) Math.ceil((0.1 * product.getPrice() * vQtdProduto) * 20) / 20;
				}
			}	
			
		}
		return salesTaxes;
	}
}
