package com.hcl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.user.exception.UserAlreadyExistsException;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.model.User;
import com.hcl.user.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/user")
public class UserController {
	
	private ResponseEntity<?> responseEntity;

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		
		try {
			@SuppressWarnings("unused")
			User existsUser = userService.saveUser(user);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("User Already Exists...!!", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> loginUser(@RequestBody User userInfo) throws UserNotFoundException{
		Map<String, String> map =new HashMap<String,String>();
		String jwtToken = "";
		try {
			User user = userService.findByUserIdAndPassword(userInfo.getUserId(), userInfo.getPassword());
			if(user.getUserId().equals(userInfo.getUserId())) {
				jwtToken = 	getToken(userInfo.getUserId(), userInfo.getPassword());
				map.put("message", "User Successfully Logged In...!!");
				map.put("token", jwtToken);
			}
			responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
	
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("User Not found...!!");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
			
		return responseEntity;
	}

	public String getToken(String userid, String password) throws Exception {
		if(userid == null || password == null) {
			throw new ServletException("Please provide proper userId and password..!!");
		}			
	   String jwtToken = Jwts.builder()
		    .setSubject(userid)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis() + 5*60*60*1000 ))
		    .signWith(SignatureAlgorithm.HS256, "capstone").compact();	
		return jwtToken;
	}
}
