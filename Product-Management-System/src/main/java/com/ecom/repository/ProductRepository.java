package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

		public List<Product> findByProductName(String productName);
	
	 	@Query("SELECT p FROM Product p ORDER BY p.price ASC")
	    List<Product> findAllProductsSortedByPriceAsc();

	    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
	    List<Product> findAllProductsSortedByPriceDesc();
	    
	    
	    @Query("SELECT p FROM Product p ORDER BY p.rating ASC")
	    List<Product> findAllProductsSortedByRatingAsc();

	    @Query("SELECT p FROM Product p ORDER BY p.rating DESC")
	    List<Product> findAllProductsSortedByRatingDesc();
	
}
