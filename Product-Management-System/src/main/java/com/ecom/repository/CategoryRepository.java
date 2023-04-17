package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Category;
import com.ecom.model.Product;
@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

	public Category findByType(String type);
	
}
