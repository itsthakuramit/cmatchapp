package com.hcl.user.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.user.exception.UserAlreadyExistsException;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Autowired
	private PasswordEncoder encoder;
	

	@Override
	public User saveUser(User user) throws UserAlreadyExistsException {
		user.setCreatedDate(new Date());
		String password = user.getPassword();
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
		
		User userexisting = userRepository.findByUserId(user.getUserId());
		if (userexisting != null) 
			throw new UserAlreadyExistsException("Username already exists...!!");		
		
		return userRepository.save(user);		
	}

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User userExists = userRepository.findByUserId(userId);
		
			if (userExists == null) 
				throw new UserNotFoundException("User Not Found..!!");
			
			String pass = password;
			if (!encoder.matches(pass, userExists.getPassword())) {
				throw new UserNotFoundException("Invalid user name and password combination...!!");
			}	
		return userExists;
	}
}
