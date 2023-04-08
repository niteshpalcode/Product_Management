package com.ecom.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
	 private Users users;
	
	 @OneToMany(cascade = CascadeType.ALL)
	 Set<Product> product = new HashSet<>();
	 
		
	
}
