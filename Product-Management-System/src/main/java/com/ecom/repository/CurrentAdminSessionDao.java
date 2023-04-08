package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.model.AdminCurrentSession;

public interface CurrentAdminSessionDao extends JpaRepository<AdminCurrentSession, Long> {

	public AdminCurrentSession findByAdminKey(String adminKey);
}
