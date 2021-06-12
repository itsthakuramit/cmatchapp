package com.hcl.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.recommendation.model.Recommend;

@Repository
public interface RecommendationRepository extends MongoRepository<Recommend, String>{
	
	public Recommend findByUserId(String userId);
}
