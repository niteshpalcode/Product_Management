package com.ecom.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Users extends AbstractUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	
	@OneToOne(mappedBy = "users" , cascade = CascadeType.ALL)
     private Cart cart;
	
	@JsonIgnore
	@OneToMany( mappedBy = "users", cascade = CascadeType.ALL)
	private List<Orders> orders;

	
	
}
