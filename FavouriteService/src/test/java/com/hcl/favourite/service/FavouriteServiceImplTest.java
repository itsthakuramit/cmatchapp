package com.hcl.favourite.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.hcl.favourite.exception.MatchAlreadyExistsException;
import com.hcl.favourite.model.Match;
import com.hcl.favourite.model.User;
import com.hcl.favourite.repository.FavouriteRepository;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class FavouriteServiceImplTest {

	@Mock
	private FavouriteRepository favRepository;

	@InjectMocks
	public FavouriteServiceImpl favouriteServiceImpl;

	private User user;

	private Match match;

	private List<Match> list;

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

		user = new User();
		user.setUserId("santhosh");
		user.setMatchList(list);
	}

	@After
	public void delete() {
		user = null;
	}

	@Test
	public void saveMatchToFavoriteListTest() throws MatchAlreadyExistsException {
		user = new User();
		user.setUserId("santhosh");		
		when(favRepository.findByUserId(user.getUserId())).thenReturn(user);
		boolean check =favouriteServiceImpl.saveMatchToFavorite(match, user.getUserId());
		assertNotNull(check);
	}
	
	@Test
	public void testgetAllFavoriteList() throws Exception {
		Mockito.when(favRepository.findByUserId(user.getUserId())).thenReturn(user);
		List<?> fetchedList = favouriteServiceImpl.getMatchList(user.getUserId());
		Assert.assertEquals(fetchedList, list);
		verify(favRepository, times(1)).findByUserId(user.getUserId());

	}

}
