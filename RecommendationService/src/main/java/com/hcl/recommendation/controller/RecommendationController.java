package com.hcl.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.recommendation.exception.MatchAlreadyExistsException;
import com.hcl.recommendation.exception.MatchNotFoundException;
import com.hcl.recommendation.model.Match;
import com.hcl.recommendation.service.RecommendationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/recommendation")
public class RecommendationController {

	private ResponseEntity<?> responseEntity;

	private RecommendationService recommendationService;

	public RecommendationController() {
		super();
	}

	@Autowired
	public RecommendationController(RecommendationService recommendationService) {
		super();
		this.recommendationService = recommendationService;
	}

	@GetMapping("/user/{userid}/getmatchlist")
	public ResponseEntity<?> getAllRecommends(@PathVariable String userid){
		try {
			List<Match> matchList = recommendationService.getRecommendList(userid);
			responseEntity = new ResponseEntity<Object>(matchList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping("/user/{userid}/addmatch")
	public ResponseEntity<?> addMatch(@RequestBody Match match, @PathVariable String userid) throws MatchAlreadyExistsException{

		if(recommendationService.saveRecommend(match, userid))
			responseEntity = new ResponseEntity<String>("Recommendation added...!!", HttpStatus.CREATED);
		 else
			responseEntity = new ResponseEntity<String>("Failed to added...!! ", HttpStatus.CONFLICT);		
		return responseEntity;
	}
	
	@PutMapping("/user/{userId}/deletematch")
	public ResponseEntity<?> deleteFromList(@RequestBody Match match, @PathVariable String userId) throws MatchNotFoundException {

		try {
			recommendationService.deleteRecommend(match.getMatchId(), userId);
			responseEntity = new ResponseEntity<String>("Remove from Recommendation...!!", HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
