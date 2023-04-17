package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
