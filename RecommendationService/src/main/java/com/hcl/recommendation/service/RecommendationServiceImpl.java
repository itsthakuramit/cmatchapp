package com.hcl.recommendation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.recommendation.exception.MatchAlreadyExistsException;
import com.hcl.recommendation.exception.MatchNotFoundException;
import com.hcl.recommendation.model.Match;
import com.hcl.recommendation.model.Recommend;
import com.hcl.recommendation.repository.RecommendationRepository;

@Service
public class RecommendationServiceImpl implements RecommendationService {

	private RecommendationRepository recommendationRepository;

	@Autowired
	public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
		super();
		this.recommendationRepository = recommendationRepository;
	}

	public boolean saveRecommend(Match match, String userid) throws MatchAlreadyExistsException {
		boolean status = false;

		if (!recommendationRepository.findById(userid).isEmpty()) {
			if (recommendationRepository.findById(userid).isPresent()) {

				Recommend recommend = recommendationRepository.findById(userid).get();
				List<Match> matchList = recommend.getMatchList();

				ListIterator<Match> listItr = matchList.listIterator();
				while (listItr.hasNext()) {
					Match m = (Match) listItr.next();

					if (m.getMatchId().equals(match.getMatchId())) {
						listItr.remove();
					}
				}
				matchList.add(match);
				recommend.setMatchList(matchList);
				recommendationRepository.save(recommend);
				status = true;
			}
		} else {
			List<Match> matchList = new ArrayList<>();
			Recommend recommend = new Recommend();
			recommend.setUserId(userid);

			matchList.add(match);

			recommend.setMatchList(matchList);
			recommendationRepository.save(recommend);
			status = true;
		}
		return status;
	}
	
	
	public boolean deleteRecommend(Long trackId, String userid) throws MatchNotFoundException {
		boolean status = false;
		if (!recommendationRepository.findById(userid).isEmpty()) {
			List<Match> matchlist = recommendationRepository.findById(userid).get().getMatchList();

			ListIterator<Match> listItr = matchlist.listIterator();
			while (listItr.hasNext()) {
				Match match = (Match) listItr.next();

				if (match.getMatchId().equals(trackId)) {
					listItr.remove();
				}
			}
			Recommend recommend = new Recommend();
			recommend.setUserId(userid);
			recommend.setMatchList(matchlist);
			recommendationRepository.save(recommend);
			status = true;
		}
		
		return status;
	}

	public List<Match> getRecommendList(String userId) throws Exception {
		Recommend recommend = recommendationRepository.findByUserId(userId);
		return recommend.getMatchList();
	}

}
