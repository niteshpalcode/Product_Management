package com.ecom.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.model.Admin;
import com.ecom.model.AdminCurrentSession;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.CurrentAdminSessionDao;

@Service
public class AdminServiceImpl implements AdminSevice {

	 private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;
	

	@Override
	public Admin saveNewAdminDetails(Admin admin) throws AdminNotFoundException {
		  logger.info("Attempting to save new admin details for username: {}", admin.getUsername());

		Admin admins = adminRepository.findByUsername(admin.getUsername());
		 
		 if(admins==null) {
			 logger.info("New admin details saved successfully for username: {}", admin.getUsername());
			return adminRepository.save(admin);
		 }else {
			 logger.error("Admin with username {} already exists", admin.getUsername());
			 throw new AdminNotFoundException("Admin with this username is already present");
		 }
		
		
	}

	@Override
	public Admin updateAdmin(String key,Admin admin ) throws AdminNotFoundException {
		 logger.info("Attempting to update admin details with key: {}", key);

		   AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(key);

		    if (isPresent == null) {
		    	   logger.error("Admin not present with key: {}", key);
		           
		        throw new AdminNotFoundException("Admin not present with this key");
		    }

		    Optional<Admin> existingAdmin = adminRepository.findById(isPresent.getAdminId());
		    if (existingAdmin.isPresent()) {
		        Admin updatedAdmin = existingAdmin.get();
		        updatedAdmin.setAddress(admin.getAddress());
		        updatedAdmin.setEmail(admin.getEmail());
		        updatedAdmin.setMobileNumber(admin.getMobileNumber());
		        updatedAdmin.setName(admin.getName());
		        updatedAdmin.setPassword(admin.getPassword());
		        updatedAdmin.setUsername(admin.getUsername());
		        logger.info("Admin details updated successfully for username: {}", admin.getUsername());
		         
		        adminRepository.save(updatedAdmin);
		    }

		    return admin;
	}

	@Override
	public String deleteAdmin(String key) throws AdminNotFoundException {
		 logger.info("Attempting to delete admin with key: {}", key);

		 AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(key);

		    if (isPresent == null) {
		    	   logger.error("Admin not present with key: {}", key);
		    	      
		    	throw new AdminNotFoundException("Admin not present with this key");
		    }

		    Optional<Admin> existingAdmin = adminRepository.findById(isPresent.getAdminId());
		    if (existingAdmin.isPresent()) {
		    	 logger.info("Admin deleted successfully with key: {}", key);
		    	adminRepository.delete(existingAdmin.get());
		    	
		    
		    }

			return "Admin with this key is Successfully Deleted";
		    
		    }
		
		
	
	
}
