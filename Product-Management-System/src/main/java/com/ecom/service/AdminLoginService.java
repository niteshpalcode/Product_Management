package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.dto.AdminLoginDto;
import com.ecom.exceptions.AdminNotFoundException;

@Service
public interface AdminLoginService {

	public String logIntoAccount(AdminLoginDto adminLoginDto) throws AdminNotFoundException;
	public String logoutFromAccount(String key) throws AdminNotFoundException;
	
	
}
