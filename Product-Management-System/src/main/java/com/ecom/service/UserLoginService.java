package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.dto.UserLoginDto;
import com.ecom.exceptions.LoginException;

@Service
public interface UserLoginService {

	public String logIntoAccount(UserLoginDto userLoginDto) throws LoginException;
	public String logoutFromAccount(String key) throws LoginException;
}
