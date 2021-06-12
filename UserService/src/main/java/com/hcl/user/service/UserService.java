package com.hcl.user.service;

import com.hcl.user.exception.UserAlreadyExistsException;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.model.User;

public interface UserService {

	public User saveUser(User user) throws UserAlreadyExistsException;

	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException ;
}
