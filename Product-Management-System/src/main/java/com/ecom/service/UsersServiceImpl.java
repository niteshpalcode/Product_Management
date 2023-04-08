package com.ecom.service;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.UsersDto;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Cart;
import com.ecom.model.CurrentUserSession;
import com.ecom.model.Orders;
import com.ecom.model.Users;
import com.ecom.repository.CartRepository;
import com.ecom.repository.CurrentUserSessionDao;
import com.ecom.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	

	@Override
	public Users addNewUsers(Users users) throws UsersNotFoundException {
	Users user  = usersRepository.findByMobileNumber(users.getMobileNumber());
		
//		if(user!=null) {
//			throw new  UsersNotFoundException("Users with this mobile number is Already exists...");
//			
//		}
			usersRepository.save(users);
			Cart cart = new Cart();
			cart.setUsers(users);
			cartRepository.save(cart);
	        user.setCart(cart);
       
       return users;

	}


	@Override
	public UsersDto updateUser(Long userId, UsersDto usersDto) throws UsersNotFoundException,LoginException {
		
		Optional<Users> isUser=usersRepository.findById(userId);
		if(isUser.isPresent()) {
			Users users=isUser.get();
			Optional<CurrentUserSession> isCurrent=currentUserSessionDao.findById(users.getUserId());
			if(isCurrent.isPresent()) {

			    users.getAddress().setCity(usersDto.getCity());
			    users.getAddress().setCountry(usersDto.getCountry());
			    users.getAddress().setState(usersDto.getState());
			    users.getAddress().setPincode(usersDto.getPincode());
			    users.setEmail(usersDto.getEmail());
			    users.setName(usersDto.getName());
			    users.setMobileNumber(usersDto.getMobileNumber());
			    users.setPassword(usersDto.getPassword());
			    users.setUsername(usersDto.getUsername());

			    usersRepository.save(users);
			    return usersDto;
			}
			else {
				throw new LoginException("User is not logged in..");
			}
		}else {
			throw new UsersNotFoundException("User is not found with this id-"+userId);
		}

		
	}


	@Override
	public String deleteUser(Long userId) throws UsersNotFoundException ,LoginException{
		
		Optional<Users> isUserPresent=usersRepository.findById(userId);
		if(isUserPresent.isPresent()) {
			Users user=isUserPresent.get();
			Optional<CurrentUserSession> isCurrent=currentUserSessionDao.findById(user.getUserId());
			if(isCurrent.isPresent()) {
				currentUserSessionDao.delete(isCurrent.get());
				usersRepository.delete(user);
				
			}else {
				throw new LoginException("user is not logged in");
			}
		}
		else {
			throw new UsersNotFoundException("No user with this userId-"+userId);
		}
		return "User is deleted successfully";
	}
	
	
	
}
