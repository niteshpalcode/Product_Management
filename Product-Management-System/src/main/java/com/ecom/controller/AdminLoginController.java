package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.AdminLoginDto;
import com.ecom.dto.UserLoginDto;
import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.exceptions.LoginException;
import com.ecom.service.AdminLoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ecom/admin")
public class AdminLoginController {

	@Autowired
	private AdminLoginService adminLoginService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginHandler(@RequestBody  AdminLoginDto adminLoginDto) throws AdminNotFoundException{
		
		return new  ResponseEntity<String>(adminLoginService.logIntoAccount(adminLoginDto) ,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> userLogoutHandler(String key) throws AdminNotFoundException{
		
		return new ResponseEntity<String>(adminLoginService.logoutFromAccount(key),HttpStatus.OK);
	}
}
