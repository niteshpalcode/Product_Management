package com.ecom.service;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.ecom.dto.UsersDto;
import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Users;

@Service
public interface UsersService {

	
	public Users addNewUsers(Users users)  throws UsersNotFoundException;
	
	public UsersDto updateUser(Long userId,UsersDto usersDto) throws UsersNotFoundException,LoginException;
	
	public String deleteUser(Long userId)throws UsersNotFoundException,LoginException;
}
