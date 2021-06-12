package com.hcl.recommendation.service;

import java.util.List;

import com.hcl.recommendation.exception.MatchAlreadyExistsException;
import com.hcl.recommendation.exception.MatchNotFoundException;
import com.hcl.recommendation.model.Match;

public interface RecommendationService {

	public boolean saveRecommend(Match match, String userId) throws MatchAlreadyExistsException;
	
	public List<Match> getRecommendList(String userId) throws Exception;

	public boolean deleteRecommend(Long matchId, String userId) throws MatchNotFoundException;

}
