package com.ecom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AdminLoginServiceImpl implements AdminLoginService {

	private static final Logger logger = LoggerFactory.getLogger(AdminLoginServiceImpl.class);

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;

	@Override
	public String logIntoAccount(AdminLoginDto adminLoginDto) throws AdminNotFoundException {

		logger.info("Attempting to log into account for username: {}", adminLoginDto.getUsername());

		Admin admin = adminRepository.findByUsername(adminLoginDto.getUsername());
		if (admin == null) {
			logger.error("Admin not present with username: {}", adminLoginDto.getUsername());
			throw new AdminNotFoundException("Admin not present with this usename");
		}
		Optional<AdminCurrentSession> isValid = currentAdminSessionDao.findById(admin.getAdminId());

		if (isValid.isPresent()) {
			logger.error("Admin with username {} is already logged in", adminLoginDto.getUsername());
			throw new AdminNotFoundException("admin with this username is already present");
		}
		if (admin.getPassword().equals(adminLoginDto.getPassword())) {
			String key = RandomString.make(4);
			AdminCurrentSession adminCurrentSession = new AdminCurrentSession(admin.getAdminId(), key,
					LocalDateTime.now());
			currentAdminSessionDao.save(adminCurrentSession);
			logger.info("Logged in successfully for username: {}", adminLoginDto.getUsername());
			return "Loggin Successfull with this key :" + key;
		} else {
			logger.error("Incorrect password for username: {}", adminLoginDto.getUsername());
			throw new AdminNotFoundException("password Incorrect...");
		}

	}

	@Override
	public String logoutFromAccount(String key) throws AdminNotFoundException {
		logger.info("Attempting to log out with key: {}", key);
		AdminCurrentSession adminCurrentSession = currentAdminSessionDao.findByAdminKey(key);
		if (adminCurrentSession == null) {
			logger.error("Incorrect key provided for logout: {}", key);
			throw new AdminNotFoundException("Incorrect key..");

		}
		currentAdminSessionDao.delete(adminCurrentSession);
		logger.info("Logged out successfully with key: {}", key);
		return "Logout From Account..";
	}

}
