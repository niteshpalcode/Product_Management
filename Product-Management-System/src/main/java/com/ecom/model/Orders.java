package com.ecom.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.catalina.User;

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
public class Orders {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long orderId;
	private Double totalAmount;
	private String paymentType;
    private LocalDate orderDate;
	private LocalTime orderTime;
	private String status;
	
	@ManyToOne
	private Users users;
	
	@OneToOne
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ProductBill productBill;
	
	
	
	
	
}
