package com.ecom.dto;

import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;






@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {

	
	private String username;
	private String password;
	private String name;
	private String mobileNumber;
	private String email;
	private String city;
	private String state;
	private String country;
	private String pincode;
	
}
