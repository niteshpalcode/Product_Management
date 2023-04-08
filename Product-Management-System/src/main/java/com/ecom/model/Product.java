package com.ecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class Product {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
//	@NotNull(message ="productname should not be null")
	private String productName;
//	@NotNull(message ="product description should not be null")
	private String descprition;
	
//	@NotNull(message ="product price should not be null")
	private Double price;
//	@NotNull(message ="product stock should not be null")
	private Integer stock;
	
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;
	

	
	
}
