package com.spring.salestaxes.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import com.spring.salestaxes.model.ProductsModel;
import com.spring.salestaxes.model.ShoppingCartModel;
import com.spring.salestaxes.service.ProductsService;
import com.spring.salestaxes.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
	private List<ShoppingCartModel> items = new ArrayList<ShoppingCartModel>();

	@Autowired
	ProductsService productsService;
	
	@Autowired
	ShoppingCartService shoppingcartService;
	
	@GetMapping(value="/shoppingcart")
	public ModelAndView getShoppingCart() {
		ModelAndView mv = new ModelAndView("shoppingcart");
		mv.addObject("items", items);
		
		Double valorTotal = shoppingcartService.getTotal(items);
		valorTotal = valorTotal == null ? 0.00 : valorTotal;
		mv.addObject("valorTotal", valorTotal);
		
		Double salesTaxes = shoppingcartService.getSalesTaxes(items);
		salesTaxes = salesTaxes == null ? 0.00 : salesTaxes;
		mv.addObject("salesTaxes", salesTaxes);
		
		return mv;	
	}
	
	@GetMapping(value="/shoppingcart/{id}")
	public ModelAndView addToShoppingCart(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("shoppingcart");
		
		ProductsModel product = productsService.findById(id);
		System.out.println("produto id: " + product.getId());
		
		ShoppingCartModel shoppingcart = new ShoppingCartModel();
		shoppingcart.setIdProduto(product.getId());
		System.out.println("shoppingcart id: " + product.getId());
		shoppingcart.setCover64(product.getCover64());
		
		boolean flagItemNaLista = false;
		
		for (ShoppingCartModel item : items) {
			if (item.getIdProduto() == shoppingcart.getIdProduto()) {
				flagItemNaLista = true;
				shoppingcart = item;
			}
		}
		
		System.out.println("flagItemNaLista: " + flagItemNaLista);
		
		if (flagItemNaLista) {
			shoppingcart.setQtdProduto(shoppingcart.getQtdProduto() + 1);
			shoppingcart.setValorCompra(shoppingcart.getQtdProduto() * shoppingcart.getValorUnitario());
			System.out.println("shoppingcart.getQtdProduto: " + shoppingcart.getQtdProduto());
			System.out.println("shoppingcart.getValorCompra: " + shoppingcart.getValorCompra());
		} else {
			shoppingcart.setNomeProduto(product.getName());
			shoppingcart.setValorUnitario(product.getPrice());
			shoppingcart.setCategory(product.getCategory());
			shoppingcart.setImported(product.getImported());
			shoppingcart.setQtdProduto(shoppingcart.getQtdProduto() + 1); // entrada no form do HTML?
			shoppingcart.setValorCompra(shoppingcart.getQtdProduto() * shoppingcart.getValorUnitario());

			if (shoppingcart.getImported() == 1) {
				if (shoppingcart.getCategory().equalsIgnoreCase("Books") || 
					shoppingcart.getCategory().equalsIgnoreCase("Food") ||
					shoppingcart.getCategory().equalsIgnoreCase("Medical Products")) {
					shoppingcart.setValorCompra(1.05 * shoppingcart.getValorCompra());
				} else {
					shoppingcart.setValorCompra(1.15 * shoppingcart.getValorCompra());
				}
			} else {
				if (shoppingcart.getCategory().equalsIgnoreCase("Books") || 
					shoppingcart.getCategory().equalsIgnoreCase("Food") ||
					shoppingcart.getCategory().equalsIgnoreCase("Medical Products")) {
					shoppingcart.setValorCompra(shoppingcart.getValorCompra());
				} else {
					shoppingcart.setValorCompra(1.1 * shoppingcart.getValorCompra());
				}
			}	

			System.out.println("shoppingcart.getQtdProduto: " + shoppingcart.getQtdProduto());
			System.out.println("shoppingcart.getValorCompra: " + shoppingcart.getValorCompra());

			items.add(shoppingcart);
		}		

		mv.addObject("items", items);
		
		Double valorTotal = shoppingcartService.getTotal(items);
		valorTotal = valorTotal == null ? 0.00 : valorTotal;
		mv.addObject("valorTotal", valorTotal);
		
		Double salesTaxes = shoppingcartService.getSalesTaxes(items);
		salesTaxes = salesTaxes == null ? 0.00 : salesTaxes;
		mv.addObject("salesTaxes", salesTaxes);
		
		System.out.println("valorTotal: " + valorTotal);
		System.out.println("salesTaxes: " + salesTaxes);
		
		return mv;	
	}
	
	@GetMapping(value="/shoppingcart/empty")
	public ModelAndView emptyShoppingCart() {
		ModelAndView mv = new ModelAndView("shoppingcart");
		
		items.clear();
		mv.addObject("items", items);
		
		Double valorTotal = shoppingcartService.getTotal(items);
		valorTotal = valorTotal == null ? 0.00 : valorTotal;
		mv.addObject("valorTotal", valorTotal);
		
		Double salesTaxes = shoppingcartService.getSalesTaxes(items);
		salesTaxes = salesTaxes == null ? 0.00 : salesTaxes;
		mv.addObject("salesTaxes", salesTaxes);
		
		return mv;
	}
}
