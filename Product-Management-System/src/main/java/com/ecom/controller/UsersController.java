package com.ecom.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.UsersDto;
import com.ecom.exceptions.LoginException;
import com.ecom.exceptions.UsersNotFoundException;
import com.ecom.model.Users;
import com.ecom.service.UsersService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/ecom/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@PostMapping("/add")
	public ResponseEntity<Users> my_InsertNewUsersHandler( @RequestBody Users users) throws UsersNotFoundException{
		
		return new ResponseEntity<Users>(usersService.addNewUsers(users),HttpStatus.CREATED);
		
	}
	@PutMapping("/update/{userId}")
	public ResponseEntity<UsersDto> updateUsersHandler(@PathVariable("userId") Long userId,UsersDto usersDto) throws UsersNotFoundException,LoginException{
		return new ResponseEntity<UsersDto>(usersService.updateUser(userId, usersDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{deleteId}")
	public ResponseEntity<String> deleteUsersHandler(@PathVariable("deleteId") Long deleteId) throws UsersNotFoundException,LoginException{
		return new ResponseEntity<String>(usersService.deleteUser(deleteId),HttpStatus.OK);
	}
	
}
