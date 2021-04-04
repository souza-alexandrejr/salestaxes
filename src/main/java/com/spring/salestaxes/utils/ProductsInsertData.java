package com.spring.salestaxes.utils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import com.spring.salestaxes.model.ProductsModel;
import com.spring.salestaxes.repository.ProductsRepository;

public class ProductsInsertData {

	@Autowired
	ProductsRepository salestaxesRepository;
	
	@PostConstruct
	public void saveProducts() {
		List<ProductsModel> productList = new ArrayList<ProductsModel>();
		
		ProductsModel product1 = new ProductsModel();
		product1.setName("Memorias Postumas de Bras Cubas");
		product1.setPrice(13.89);
		product1.setCategory("books");
		product1.setImported(0);
		
		productList.add(product1);
		
		for (ProductsModel product : productList) {
			ProductsModel productSaved = salestaxesRepository.save(product);
			System.out.println("Product " + productSaved.getId() + " saved at database with success!");
		}
	}
	
	/*
	 
	INSERT INTO products 
		(NAME, price, category, imported)
	VALUES 
		("Harry Potter", 35.99, "book", TRUE),
		("The Chronicles of Narnia", 65.99, "book", TRUE),
		("The Handmaid's Tale", 24.99, "book", FALSE),
		("Brave New World", 12.49, "book", FALSE),
		("Game of Thrones", 35.99, "book", TRUE),
		("212 For Men", 47.50, "medical products", TRUE),
		("Dior Colonia", 27.99, "medical products", TRUE),
		("Kaiak Natura", 18.99, "medical products", FALSE),
		("Dorflex", 9.75, "medical products", FALSE),
		("Bahlsen Choco Leibniz", 11.25, "food", TRUE),
		("Hershey's Pot of Gold", 10.00, "food", TRUE),
		("Diamante Negro", 0.85, "food`produtos`", FALSE),
		("Pizza", 35.99, "food", FALSE),
		("Ceaser Salad", 35.99, "food", FALSE),
		("PC Monitor", 35.99, "eletronics", FALSE),
		("Keyboard", 35.99, "eletronics", FALSE),
		("MAC Notebook", 35.99, "eletronics", TRUE),
		("Pitty - Matriz", 14.99, "music CD", FALSE),
		("The Strokes - The New Abnormal", 25.99, "music CD", TRUE);
		
		
	SELECT * FROM products;
	
	 */
}
