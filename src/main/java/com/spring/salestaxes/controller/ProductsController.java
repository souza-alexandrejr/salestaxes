package com.spring.salestaxes.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.spring.salestaxes.model.ProductsModel;
import com.spring.salestaxes.service.ProductsService;

@Controller
public class ProductsController {
	
	@Autowired
	ProductsService productsService;
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ModelAndView getProducts() {
		ModelAndView mv = new ModelAndView("products");
		List<ProductsModel> products = productsService.findAll();
		mv.addObject("products", products);
		
		Set<String> categories = new HashSet<String>();
		
		for (ProductsModel product : products) {
			categories.add(product.getCategory());
		}
		
		mv.addObject("categories", categories);
		
		return mv;		
	}
	
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
	public ModelAndView getProductById(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("product-details");
		ProductsModel product = productsService.findById(id);
		mv.addObject("product", product);
		return mv;		
	}
}
