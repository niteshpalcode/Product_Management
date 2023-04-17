package com.ecom.service;

import java.util.List;

import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.Product;

public interface CartService {

	
	public Cart addProductToCart(Long productId, Long userId) 
			throws  ProductNotFoundException,UsersNotFoundException, LoginException , CartException;
	
	public String deleteProductFromCart(Long productId, Long userId)
			throws  ProductNotFoundException,UsersNotFoundException, LoginException , CartException;
	
	 public String increaseProductQuantity(Long productId, int quantity,Long userId)
    		 throws  ProductNotFoundException,UsersNotFoundException, LoginException , CartException;
     
	public String decreaseProductQuantity(Long productId, int quantity,Long userId)
				throws  ProductNotFoundException,UsersNotFoundException, LoginException , CartException;
		
	 public String clearProductFromCart(Long userId )throws CartException,LoginException,UsersNotFoundException;

	 public List<Product> viewAllProductFromCart(Long userId,Long cartId )throws CartException,LoginException,UsersNotFoundException;
		
		
}
