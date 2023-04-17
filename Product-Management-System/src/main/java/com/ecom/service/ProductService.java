package com.ecom.service;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.model.Product;

public interface ProductService {
	public Product addNewProduct(Product product,Long categoryId, String key) 
			throws ProductNotFoundException,CategoryNotFoundException,AdminNotFoundException;
	
	public String deleteProduct(Long productId, String key) throws ProductNotFoundException,AdminNotFoundException;
	
}
