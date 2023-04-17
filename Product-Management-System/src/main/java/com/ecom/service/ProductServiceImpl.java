package com.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.model.AdminCurrentSession;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.CurrentAdminSessionDao;
import com.ecom.repository.ProductRepository;

@Service
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
		Optional<Product> isPresentProduct = productRepository.findById(productId);
			if(isPresentProduct.isPresent()) {
				isPresentProduct.get().setCategory(null);
				productRepository.delete(isPresentProduct.get());
				return "Product Deleted Successfully..";
	
			}else {
				throw new ProductNotFoundException("Product not found with this productId -" + productId);
			}
	}

	@Override
	public Product findProductById(Long ProductId) throws ProductNotFoundException {
		Optional<Product> isProduct = productRepository.findById(ProductId);
		if(isProduct.isEmpty()) {
			throw new ProductNotFoundException("Product with this productId not Found-"+ ProductId);
		}
		return isProduct.get();
	}

	@Override
	public List<Product> viewAllProduct() throws ProductNotFoundException {
		List<Product> ispProducts = productRepository.findAll();
		if(ispProducts.size()==0) {
			throw new ProductNotFoundException("Product with not Found..!");
		}
		return ispProducts;
	}

	@Override
	public List<Product> findProductByName(String productName) throws ProductNotFoundException {
		List<Product> ispProducts = productRepository.findByProductName(productName);
		if(ispProducts.size()==0) {
			throw new ProductNotFoundException("Product with not name is not Found..!");
		}
		return ispProducts;
	}

	@Override
	public Product updateProductById(Long ProductId, String adminKey, Product product)
			throws ProductNotFoundException, AdminNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(ProductId);
	    if (!optionalProduct.isPresent()) {
	        throw new ProductNotFoundException("Product with id " + ProductId + " not found");
	    }
		AdminCurrentSession isPresent=currentAdminSessionDao.findByAdminKey(adminKey);
		if(isPresent==null) {
			throw new AdminNotFoundException("Admin is not found with this key--- "+ adminKey);
			
		}
		
	    Product existingProduct = optionalProduct.get();
	    existingProduct.setProductName(product.getProductName());
	    existingProduct.setPrice(product.getPrice());
	    existingProduct.setRating(product.getRating());
	    existingProduct.setDescprition(product.getDescprition());
	    existingProduct.setStock(product.getStock() );
	    
	    return   productRepository.save(existingProduct);
		
	
	}

	@Override
	public List<Product> findAllProductsSortedByPrice(String sortOrder) throws ProductNotFoundException {
		  List<Product> products = null;
	        if (sortOrder.equalsIgnoreCase("asc")) {
	            products = productRepository.findAllProductsSortedByPriceAsc();
	        } else if (sortOrder.equalsIgnoreCase("desc")) {
	            products = productRepository.findAllProductsSortedByPriceDesc();
	        }
	        return products;
	}

	@Override
	public List<Product> findProductsSortedByRatingAsc() {
		 return productRepository.findAllProductsSortedByRatingAsc();
	}

	@Override
	public List<Product> findProductsSortedByRatingDesc() {
		return productRepository.findAllProductsSortedByRatingDesc();
	}
	
}
