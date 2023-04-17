package com.ecom.model;


import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AbstractUser {


	private String username;
	private String password;
	private String name;
	
	@Embedded
	private Address address;
	private String mobileNumber;
	private String email;
	
	
}
