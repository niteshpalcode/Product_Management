package com.ecom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductManagementSystemApplication {
	 private static final Logger logger = LoggerFactory.getLogger(ProductManagementSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProductManagementSystemApplication.class, args);
		  logger.info("-- Welcome to Product Management System --");
	}

}
