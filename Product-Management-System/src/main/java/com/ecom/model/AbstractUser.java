package com.ecom.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AbstractUser {

//	@NotNull(message ="username should not be null")
//	@Size(min =3,max=15,message="length of username must be between 3 & 15")
	private String username;
	
	
//	@NotNull(message ="password should not be null")
//	@Size(min =3,max=10,message="length of username must be between 3 & 10")
//	@Pattern(regexp = "/^(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[^a-zA-Z0-9])(?!.*\\s).{6,12}$/")
	private String password;
	
	
//	@NotNull(message ="name should not be null")
	private String name;
	
	
	@Embedded
	private Address address;
//	@NotNull(message ="name should not be null")
//
//	@Column(unique = true)
	
	private String mobileNumber;
	
	
//	@NotNull(message ="email should not be null")
//	@Email(message ="email should be in the correct formate")
//    @Column(unique = true)
	
	private String email;
	
	
}
