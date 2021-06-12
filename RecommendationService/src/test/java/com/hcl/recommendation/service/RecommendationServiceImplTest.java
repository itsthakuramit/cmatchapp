package com.hcl.recommendation.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.hcl.recommendation.exception.MatchAlreadyExistsException;
import com.hcl.recommendation.model.Match;
import com.hcl.recommendation.model.Recommend;
import com.hcl.recommendation.repository.RecommendationRepository;

import junit.framework.Assert;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class RecommendationServiceImplTest {

	@Mock
	private RecommendationRepository recommendationRepository;

	private Recommend recommend;

	private Match match;

	private List<Match> list;

	@InjectMocks
	private RecommendationServiceImpl recommendationServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		match = new Match();
		match.setMatchId((long) 1234567892);
		match.setDate("date");
		match.setDateTimeGMT("DateTimeGMT");
		match.setTeam_1("Team1");
		match.setTeam_2("Team2");
		match.setType("Type");
		match.setSquad("Squad");
		match.setToss_winner_team("toss_winner_team");
		match.setWinner_team("Winner_team");
		match.setMatchStarted("match started");
		list = new ArrayList<Match>();
		list.add(match);

		recommend = new Recommend();
		recommend.setUserId("santhosh");
		recommend.setMatchList(list);
	}

	@After
	public void delete() {
		recommend = null;
	}

	@Test
	public void saveMatchToRecommendListTest() throws MatchAlreadyExistsException {
		recommend = new Recommend();
		recommend.setUserId("santhosh");		
		when(recommendationRepository.findByUserId(recommend.getUserId())).thenReturn(recommend);
		boolean check =recommendationServiceImpl.saveRecommend(match, recommend.getUserId());
		assertNotNull(check);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testgetAllFavoriteList() throws Exception {
		Mockito.when(recommendationRepository.findByUserId(recommend.getUserId())).thenReturn(recommend);
		List<?> fetchedList = recommendationServiceImpl.getRecommendList(recommend.getUserId());
		Assert.assertEquals(fetchedList, list);
		verify(recommendationRepository, times(1)).findByUserId(recommend.getUserId());

	}

}
