package com.hcl.favourite.controller;

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

import com.hcl.favourite.exception.MatchAlreadyExistsException;
import com.hcl.favourite.exception.MatchNotFoundException;
import com.hcl.favourite.model.Match;
import com.hcl.favourite.service.FavouriteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/favouriteservice")
public class FavouriteController {

	private ResponseEntity<?> responseEntity = null;

	private FavouriteService favService;

	@Autowired
	public FavouriteController(FavouriteService favService) {
		super();
		this.favService = favService;
	}

	@PutMapping("/user/{userId}/addmatch")
	public ResponseEntity<?> addMatchToFavoriteList(@RequestBody Match match, @PathVariable String userId) throws MatchAlreadyExistsException{
		if(favService.saveMatchToFavorite(match, userId))			
			responseEntity = new ResponseEntity<String>("Added to Favourite Successfully...!!", HttpStatus.CREATED);
		else 
			responseEntity = new ResponseEntity<String>("Already added to wishlist...!!", HttpStatus.CONFLICT);
	
		return responseEntity;
	}
	

	@PutMapping("/user/{userId}/deletematch")
	public ResponseEntity<?> deleteFromList(@RequestBody Match match, @PathVariable String userId)
			throws MatchNotFoundException {

		try {
			favService.deleteMatchFromFavorite(match.getMatchId(), userId);
			responseEntity = new ResponseEntity<String>("Remove from Favourite...!!", HttpStatus.OK);

		} catch (MatchNotFoundException e) {
			throw new MatchNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	

	@GetMapping("/user/{userId}/getmatchlist")
	public ResponseEntity<?> getMatchList(@PathVariable String userId) {

		try {
			List<Match> matchList = favService.getMatchList(userId);
			responseEntity = new ResponseEntity<Object>(matchList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
