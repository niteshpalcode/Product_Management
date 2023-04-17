package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.CategoryNotFoundException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ecom/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/save/{categoryId}/{adminKey}")
	public ResponseEntity<Product> addNewProductHandler
						(@PathVariable("categoryId") Long categoryId,@PathVariable("adminKey") String adminKey, @RequestBody Product product)
						throws ProductNotFoundException,CategoryNotFoundException,AdminNotFoundException{
		return new ResponseEntity<Product>(productService.addNewProduct(product, categoryId, adminKey),HttpStatus.CREATED);
	}
	@DeleteMapping("/delete/{productId}/{adminKey}")
	public ResponseEntity<String> deleteProductHandler
								(@PathVariable("productId") Long productId,@PathVariable("adminKey") String adminKey)
								throws ProductNotFoundException, AdminNotFoundException{
		return new ResponseEntity<String>(productService.deleteProduct(productId, adminKey),HttpStatus.ACCEPTED);
	}
	@GetMapping("/getAll/")
	public ResponseEntity<List<Product>> getAllProductHandler()
	throws ProductNotFoundException{
		
		return new ResponseEntity<List<Product>>(productService.viewAllProduct(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{productName}")
	public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable("productName") String productName )
	throws ProductNotFoundException{
		
		return new ResponseEntity<List<Product>>(productService.findProductByName(productName),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getAll/sortByPrice")
	public ResponseEntity<List<Product>> getSortedProductByPriceHandler
	(@RequestParam(name = "sortOrder", defaultValue = "asc") String sortOrder)
		throws ProductNotFoundException {
	    List<Product> products = productService.findAllProductsSortedByPrice(sortOrder);
	    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/getAllByRatingAsc")
    public ResponseEntity<List<Product>> getAllProductsSortedByRatingAscHandler() {
        List<Product> products = productService.findProductsSortedByRatingAsc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

   
    @GetMapping("/getAllByRatingDesc")
    public ResponseEntity<List<Product>> getAllProductsSortedByRatingDescHandler() {
        List<Product> products = productService.findProductsSortedByRatingDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	
	
	@GetMapping("/getAll/{productId}")
	public ResponseEntity<Product> getProductByIdHandler(@PathVariable("productId") Long productId)
	throws ProductNotFoundException{
		
		return new ResponseEntity<Product>(productService.findProductById(productId),HttpStatus.ACCEPTED);
	}
	 @PutMapping("/update/{productId}/{adminKey}")
	    public ResponseEntity<Product> updateProductById
	    (@PathVariable("productId") Long ProductId, @PathVariable("adminKey") String adminKey, Product product) 
	    		throws ProductNotFoundException, AdminNotFoundException
	    		{
	    	
	    	return new ResponseEntity<Product>(productService.updateProductById(ProductId, adminKey, product),HttpStatus.ACCEPTED);
	    }
}
