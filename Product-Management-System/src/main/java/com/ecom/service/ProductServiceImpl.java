package com.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.model.AdminCurrentSession;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.CurrentAdminSessionDao;
import com.ecom.repository.ProductRepository;

public class ProductServiceImpl implements ProductService{

	
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product addNewProduct(Product product, Long categoryId, String key)
			throws ProductNotFoundException, CategoryNotFoundException, AdminNotFoundException {
		AdminCurrentSession isPresent=currentAdminSessionDao.findByAdminKey(key);
		if(isPresent==null) {
			throw new AdminNotFoundException("No Admin found with this key--- "+ key);
			
		}
		Optional<Category> isCategory=categoryRepository.findById(categoryId);
		if(!isCategory.isPresent()) {
			throw new CategoryNotFoundException("No category is found  with this category");
		}
		Category category=isCategory.get();
		category.getList().add(product);
		
		product.setCategory(category);
		
		productRepository.save(product);
		
		return product;
	}

	@Override
	public String deleteProduct(Long productId, String key) throws ProductNotFoundException, AdminNotFoundException {
		AdminCurrentSession isPresent=currentAdminSessionDao.findByAdminKey(key);
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin is not found with this key--- "+ key);
			
		}
		Optional<Product> isProduct=productRepository.findById(productId);
		if(!isProduct.isPresent()) {
			throw new ProductNotFoundException("Product is not found with this id-"+productId);
		}
		
		productRepository.delete(isProduct.get());
		
	    return "Product deleted successfully";
	}
	
}
