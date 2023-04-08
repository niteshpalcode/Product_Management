package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);
	public Users findByMobileNumber(String mobileNumber);
}
