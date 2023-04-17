package com.ecom.model;


import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long cartId;
	private Double price;
	private Integer quantity;
	private String imageUrl;
	
	@OneToOne
	@JsonIgnore
	private Users users;
	   
	
	 @OneToMany(cascade = CascadeType.ALL)
	 @JsonIgnore
	 List<Product> product;
	 
		
	
}
