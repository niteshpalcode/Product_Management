package com.ecom.service;

import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.OrderNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Orders;


public interface OrderService {

	 public Orders addOrderFromCart(Orders orders, Long cartId ,Long userId)
	    		throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException;
	    
	 public Orders viewOrderFromCart(Long orderId, Long userId)
				throws OrderNotFoundException,LoginException,UsersNotFoundException;
		
	 public Orders updateOrderStatus(Long orderId, String status)
				throws OrderNotFoundException;
		
	 
	public Orders deleteOrderFromCart(Long orderId, Long userId)
				throws OrderNotFoundException,LoginException,UsersNotFoundException;
}
