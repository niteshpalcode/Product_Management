package com.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.CartException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.OrderNotFoundException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.CurrentUserSession;
import com.ecom.model.Orders;
import com.ecom.model.ProductBill;
import com.ecom.model.Users;
import com.ecom.repository.CartRepository;
import com.ecom.repository.CurrentUserSessionDao;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.UsersRepository;



@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
		
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Override
	public Orders addOrderFromCart(Orders orders, Long cartId, Long userId)
			throws OrderNotFoundException, CartException, LoginException, UsersNotFoundException {
		if (cartId == null) {
	        throw new IllegalArgumentException("carttId cannot be null");
	    }
	    if (userId == null) {
	        throw new IllegalArgumentException("userId cannot be null");
	    }
	    
	    Optional<Users> optionalUser = usersRepository.findById(userId);
	    if (!optionalUser.isPresent()) {
	        throw new UsersNotFoundException("User with ID " + userId + " not found");
	    }
	    Users users = optionalUser.get();
	    
	    
	    Optional<CurrentUserSession> isCurrent = currentUserSessionDao.findById(users.getUserId());
	    if(!isCurrent.isPresent()) {
	    	throw new LoginException("User is not Loggin With this userId"+ userId);
	    }
	    
	    Optional<Cart> isCartPresent = cartRepository.findById(cartId);
	    if(!isCartPresent.isPresent()) {
	    	throw new CartException("Cart Not Found With this cartId -"+ cartId);
	    }

	    
	    orders.setCart(isCartPresent.get());
	    orders.setUsers(isCartPresent.get().getUsers());
	    return orderRepository.save(orders);
	}

	@Override
	public Orders viewOrderFromCart(Long orderId, Long userId)
			throws OrderNotFoundException, LoginException, UsersNotFoundException {
		if (orderId == null) {
	        throw new IllegalArgumentException("orderId cannot be null");
	    }
	    if (userId == null) {
	        throw new IllegalArgumentException("userId cannot be null");
	    }
	    
	    Optional<Users> optionalUser = usersRepository.findById(userId);
	    if (!optionalUser.isPresent()) {
	        throw new UsersNotFoundException("User with ID " + userId + " not found");
	    }
	    Users users = optionalUser.get();
	    
	    
	    Optional<CurrentUserSession> isCurrent = currentUserSessionDao.findById(users.getUserId());
	    if(!isCurrent.isPresent()) {
	    	throw new LoginException("User is not Loggin With this userId"+ userId);
	    }
	    
	    Optional<Orders> isCurrentOrder = orderRepository.findById(orderId);
	    if(!isCurrentOrder.isPresent()) {
	    	throw new OrderNotFoundException("Order is not found With this orderId"+ orderId);
	    }
	    
	    
	    return isCurrentOrder.get();
	}

	@Override
	public Orders updateOrderStatus(Long orderId, String status) throws OrderNotFoundException {
		 Optional<Orders> isCurrentOrder = orderRepository.findById(orderId);
		    if(!isCurrentOrder.isPresent()) {
		    	throw new OrderNotFoundException("Order is not found With this orderId"+ orderId);
		    }
		    isCurrentOrder.get().setStatus(status);
		    
		return isCurrentOrder.get();
	}

	@Override
	public Orders deleteOrderFromCart(Long orderId, Long userId) 
			throws OrderNotFoundException,LoginException,UsersNotFoundException{

		if (orderId == null) {
	        throw new IllegalArgumentException("orderId cannot be null");
	    }
	    if (userId == null) {
	        throw new IllegalArgumentException("userId cannot be null");
	    }
	    
	    Optional<Users> optionalUser = usersRepository.findById(userId);
	    if (!optionalUser.isPresent()) {
	        throw new UsersNotFoundException("User with ID " + userId + " not found");
	    }
	    Users users = optionalUser.get();
	    
	    
	    Optional<CurrentUserSession> isCurrent = currentUserSessionDao.findById(users.getUserId());
	    if(!isCurrent.isPresent()) {
	    	throw new LoginException("User is not Loggin With this userId"+ userId);
	    }
	    
	    Optional<Orders> isCurrentOrder = orderRepository.findById(orderId);
	    if(!isCurrentOrder.isPresent()) {
	    	throw new OrderNotFoundException("Order is not found With this orderId"+ orderId);
	    }
	    Orders orders =  isCurrentOrder.get();
    
	    if (!orders.getCart().getUsers().equals(users)) {
	        throw new OrderNotFoundException("Order with ID " + orderId + " does not belong to the user's cart");
	    }

    	orders.setCart(null);
    	orders.setUsers(null);
  
    	orderRepository.delete(orders);
    	return orders;
	}

}
