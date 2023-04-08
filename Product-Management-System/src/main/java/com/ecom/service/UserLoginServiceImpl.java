package com.ecom.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.UserLoginDto;
import com.ecom.exceptions.LoginException;
import com.ecom.model.CurrentUserSession;
import com.ecom.model.Users;
import com.ecom.repository.CurrentUserSessionDao;
import com.ecom.repository.UsersRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    
	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
	public String logIntoAccount(UserLoginDto userLoginDto) throws LoginException {
	 
		Users isPresent = usersRepository.findByUsername(userLoginDto.getUsername());
		
		if(isPresent==null) {
			throw new  LoginException("username is not valid please put correct username");
		}
		
		Optional<CurrentUserSession> validCurrentSession = currentUserSessionDao.findById(isPresent.getUserId());
			if(validCurrentSession.isPresent()) {
				throw new LoginException("user with this username" +isPresent.getUsername()+"is Already logged in.");
			}
			
			if(isPresent.getPassword().equals(userLoginDto.getPassword())) {
				String key = RandomString.make(5);
				CurrentUserSession currentUserSession = new CurrentUserSession(isPresent.getUserId(),key, LocalDateTime.now());
				currentUserSessionDao.save(currentUserSession);
				return "Login successfully" + "and your generate key is" +currentUserSession.getUserKey();
			}else {
				throw new LoginException("Please Enter correct password..");
			}
		
		
		
	}

	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		
		CurrentUserSession currentUserSession = currentUserSessionDao.findByUserKey(key);
		if(currentUserSession==null) {
			throw new LoginException("your generated session id is not correct please enter correct key");
		}else {
			currentUserSessionDao.delete(currentUserSession);
			return "Logged Out From Account Successfully.";
		}
		
	}
	
	
}
