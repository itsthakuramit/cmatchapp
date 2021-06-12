package com.hcl.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByUserId(String userId);
	
	User findByUserIdAndPassword(String userId, String password);
  
}
