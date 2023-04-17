
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
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.Product;
import com.ecom.service.CartService;


@RestController
@RequestMapping("/ecom/cart")
public class CartController {

	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add/{productId}/{userId}")
	public ResponseEntity<Cart> addToCartHandler(@PathVariable ("productId") Long  productId, @PathVariable ("userId")Long userId) throws   ProductNotFoundException,UsersNotFoundException, 
	LoginException , CartException{
		
		Cart cart = cartService.addProductToCart(productId, userId);
		return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{productId}/{userId}")
	public ResponseEntity<String> removeProductFromCartHandler
	(@PathVariable ("productId") Long  productId, @PathVariable ("userId")Long userId) 
			throws   ProductNotFoundException,UsersNotFoundException, LoginException , CartException{
		
		String cart = cartService.deleteProductFromCart(productId, userId);
		return new ResponseEntity<String>(cart,HttpStatus.OK);
	}
	
	@GetMapping("getAllProduct/{userId}")
	public ResponseEntity<List<Product>> getListOfProductFromCartHandler
	(@PathVariable ("userId")Long userId)
			throws CartException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<List<Product>>(cartService.viewAllProductFromCart(userId),HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{productId}/{userId}{quantity} ")
	public ResponseEntity<String> increaseProductQuantityFromCartHandler
	(@PathVariable ("productId") Long  productId, @PathVariable ("userId")Long userId ,@PathVariable ("quantity")  Integer quantity) 
			throws   ProductNotFoundException,UsersNotFoundException, LoginException , CartException{
		
		String cart = cartService.increaseProductQuantity(productId, quantity, userId);
		return new ResponseEntity<String>(cart,HttpStatus.OK);
	}
	
	
	@PutMapping("/{productId}/{userId}/{quantity}")
	public ResponseEntity<String> decreaseProductQuantityFromCartHandler
	(@PathVariable ("productId") Long  productId, @PathVariable ("userId")Long userId , @PathVariable ("quantity") Integer quantity) 
			throws   ProductNotFoundException,UsersNotFoundException, LoginException , CartException{
		
		String cart = cartService.decreaseProductQuantity(productId, quantity, userId);
		return new ResponseEntity<String>(cart,HttpStatus.OK);
	}
	
	@PutMapping("remove/{userId}")
	public ResponseEntity<String> removeAllProductFromCartHandler
	(@PathVariable ("userId") Long  userId) 
			throws   ProductNotFoundException,UsersNotFoundException, LoginException , CartException{
		
		String cart = cartService.clearProductFromCart(userId);
		return new ResponseEntity<String>(cart,HttpStatus.OK);
	}
	
	
}
