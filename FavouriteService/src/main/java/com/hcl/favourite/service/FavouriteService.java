package com.hcl.favourite.service;

import java.util.List;

import com.hcl.favourite.exception.MatchAlreadyExistsException;
import com.hcl.favourite.exception.MatchNotFoundException;
import com.hcl.favourite.model.Match;

public interface FavouriteService {

	public boolean saveMatchToFavorite(Match match, String userId) throws MatchAlreadyExistsException;
	public boolean deleteMatchFromFavorite(Long trackId, String userId ) throws MatchNotFoundException;
	public List<Match> getMatchList(String userId ) throws Exception;
}
