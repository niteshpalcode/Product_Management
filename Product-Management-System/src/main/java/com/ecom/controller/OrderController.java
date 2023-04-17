package com.ecom.controller;
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
import com.ecom.exceptions.OrderNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Orders;
import com.ecom.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("ecom/orders")
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	

	@PostMapping("/{userId}/{cartId}")
	public ResponseEntity<Orders> proceedOrdersFromCartHandler(@PathVariable("userId") Long userId,@PathVariable("cartId") Long cartId,@RequestBody Orders orders)
			throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<Orders>(orderService.addOrderFromCart(orders, cartId, userId),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{userId}/{orderId}")
	public ResponseEntity<Orders> viewOrderHandler(@PathVariable("userId") Long userId,@PathVariable("orderId") Long orderId)
			throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<Orders>(orderService.viewOrderFromCart(orderId, userId ),HttpStatus.OK);
	}
	
	@DeleteMapping("/{orderId}/{userId}")
	public ResponseEntity<Orders> deleteOrderFromOrdersHandler(@PathVariable("orderId") Long orderId,@PathVariable("userId") Long userId)
			throws OrderNotFoundException,CartException,LoginException,UsersNotFoundException{
		
		return new ResponseEntity<Orders>(orderService.deleteOrderFromCart(orderId, userId),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/{status}")
	public ResponseEntity<Orders> updateOrdersStatusHandler(@PathVariable("orderId") Long orderId,@PathVariable("status") String status)
			throws OrderNotFoundException{
		
		return new ResponseEntity<Orders>(orderService.updateOrderStatus(orderId, status),HttpStatus.ACCEPTED);
	}
	
	
	
}
