package com.ecom.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetail> myMANVEHandler(MethodArgumentNotValidException me){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage("Validation error..!");
		error.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetail> myLEHandler(LoginException le ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(le.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<MyErrorDetail> myONFEHandler(OrderNotFoundException oe ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(oe.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<MyErrorDetail> myPNFEHandler(ProductNotFoundException pe ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(pe.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity<MyErrorDetail> myANFEHandler(AdminNotFoundException ad ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(ad.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

	}
	
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<MyErrorDetail> myCNFEHandler(CategoryNotFoundException cn ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(cn.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	@ExceptionHandler(UsersNotFoundException.class)
	public ResponseEntity<MyErrorDetail> myUNFEEHandler(UsersNotFoundException un ,WebRequest wr){
		
		MyErrorDetail error = new MyErrorDetail();
		error.setTimestamp(LocalDateTime.now());
		error.setMessage(un.getMessage());
		error.setDescription(wr.getDescription(false));
		
		return new ResponseEntity<MyErrorDetail>(error,HttpStatus.BAD_REQUEST);

		}
	
	
	
}
