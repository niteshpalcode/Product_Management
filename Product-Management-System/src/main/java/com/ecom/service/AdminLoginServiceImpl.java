package com.ecom.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.AdminLoginDto;
import com.ecom.exceptions.AdminNotFoundException;
import com.ecom.model.Admin;
import com.ecom.model.AdminCurrentSession;
import com.ecom.repository.AdminRepository;
import com.ecom.repository.CurrentAdminSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;

	@Override
	public String logIntoAccount(AdminLoginDto adminLoginDto) throws AdminNotFoundException {
	
		Admin admin = adminRepository.findByUsername(adminLoginDto.getUsername());
		if(admin==null) {
			throw new AdminNotFoundException("Admin not present with this usename");
		}
		Optional<AdminCurrentSession> isValid = currentAdminSessionDao.findById(admin.getAdminId());
		
		if(isValid.isPresent()) {
			throw new AdminNotFoundException("admin with this username is already present");
		}
		if(admin.getPassword().equals(adminLoginDto.getPassword())) {
			String key = RandomString.make(4);
			AdminCurrentSession adminCurrentSession = new AdminCurrentSession(admin.getAdminId(),key, LocalDateTime.now());
			currentAdminSessionDao.save(adminCurrentSession);
			return "Loggin Successfull with this key :" + key;
		}else {
			throw new AdminNotFoundException("password Incorrect...");
		}
		
		
		
	}

	@Override
	public String logoutFromAccount(String key) throws AdminNotFoundException {
		
		AdminCurrentSession adminCurrentSession = currentAdminSessionDao.findByAdminKey(key);
		if(adminCurrentSession==null) {
			throw new AdminNotFoundException("Incorrect key..");
			
		}
		currentAdminSessionDao.delete(adminCurrentSession);
		return "Logout From Account..";
	}
	
}
