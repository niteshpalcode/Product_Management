package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.model.Admin;

@Service
public interface AdminSevice {

	public Admin saveNewAdminDetails(Admin admin) throws AdminNotFoundException;
	public Admin updateAdmin(String key , Admin admin) throws AdminNotFoundException;
	public String deleteAdmin(String key)throws AdminNotFoundException;
	
}
