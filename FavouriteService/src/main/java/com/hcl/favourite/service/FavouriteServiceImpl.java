package com.hcl.favourite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.favourite.exception.MatchAlreadyExistsException;
import com.hcl.favourite.exception.MatchNotFoundException;
import com.hcl.favourite.model.Match;
import com.hcl.favourite.model.User;
import com.hcl.favourite.repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService {

	private FavouriteRepository favRepository;

	@Autowired
	public FavouriteServiceImpl(FavouriteRepository favRepository) {
		super();
		this.favRepository = favRepository;
	}

	@Override
	public boolean saveMatchToFavorite(Match match, String userid) throws MatchAlreadyExistsException {
		boolean status = false;
		
		if(!favRepository.findById(userid).isEmpty()) {			
			if(favRepository.findById(userid).isPresent()){
			
				User user = favRepository.findById(userid).get();
				List<Match> matchList = user.getMatchList();
				
				ListIterator<Match> listItr = matchList.listIterator();
				while (listItr.hasNext()) {
					Match m = (Match) listItr.next();

					if (m.getMatchId().equals(match.getMatchId())) {
						listItr.remove();
					}
				}
				matchList.add(match);				
				user.setMatchList(matchList);
				favRepository.save(user);
				status = true;
			}
		}else {
			List<Match> matchList = new ArrayList<>();
			User user = new User();
			user.setUserId(userid);
			
			matchList.add(match);
			
			user.setMatchList(matchList);
			favRepository.save(user);
			status = true;
		}
		
		return status;
	}


	@Override
	public boolean deleteMatchFromFavorite(Long trackId, String userid) throws MatchNotFoundException {
		boolean status = false;
		if (!favRepository.findById(userid).isEmpty()) {
			List<Match> matchlist = favRepository.findById(userid).get().getMatchList();

			ListIterator<Match> listItr = matchlist.listIterator();
			while (listItr.hasNext()) {
				Match match = (Match) listItr.next();

				if (match.getMatchId().equals(trackId)) {
					listItr.remove();
				}
			}
			User user = new User();
			user.setUserId(userid);
			user.setMatchList(matchlist);
			favRepository.save(user);
			status = true;
		} else
			throw new MatchNotFoundException();
		
		return status;
	}

	@Override
	public List<Match> getMatchList(String userId) throws Exception {
		User user = favRepository.findByUserId(userId);
		return user.getMatchList();
	}
}
