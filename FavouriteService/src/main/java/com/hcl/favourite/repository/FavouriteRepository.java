package com.hcl.favourite.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.favourite.model.User;

@Repository
public interface FavouriteRepository extends MongoRepository<User, String> {

	public User findByUserId(String userId);
}
