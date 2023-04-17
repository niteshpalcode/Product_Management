package com.ecom.service;

import java.util.List;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.model.Product;

public interface ProductService {
	public Product addNewProduct(Product product,Long categoryId, String key) 
			throws ProductNotFoundException,CategoryNotFoundException,AdminNotFoundException;
	
	public String deleteProduct(Long productId, String key) throws ProductNotFoundException,AdminNotFoundException;
	
	public Product findProductById(Long ProductId) throws ProductNotFoundException;
	
	public List<Product> viewAllProduct()throws ProductNotFoundException;
	
	public List<Product> findProductByName(String productName)throws ProductNotFoundException;
	
	public Product updateProductById(Long ProductId, String adminKey,Product product)
			throws ProductNotFoundException,AdminNotFoundException;
	
	public List<Product> findAllProductsSortedByPrice(String sortOrder) 
			throws ProductNotFoundException;
	
	public List<Product> findProductsSortedByRatingAsc();
	
	public List<Product> findProductsSortedByRatingDesc();
	
}
