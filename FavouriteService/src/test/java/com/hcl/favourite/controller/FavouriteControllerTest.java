package com.hcl.favourite.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.favourite.exception.MatchAlreadyExistsException;
import com.hcl.favourite.model.Match;
import com.hcl.favourite.model.User;
import com.hcl.favourite.service.FavouriteService;

@WebMvcTest(FavouriteController.class)
@RunWith(SpringRunner.class)
public class FavouriteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FavouriteService favService;

	@InjectMocks
	private FavouriteController favouriteController;

	private User user;

	private Match match;

	private List<Match> list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(favouriteController).build();
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
	public void testAddMatchToFavoriteList() throws Exception {
		when(favService.saveMatchToFavorite(any(), eq(user.getUserId()))).thenReturn(true);
		mockMvc.perform(put("/favouriteservice/user/{userId}/addmatch", user.getUserId())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isCreated())
				.andDo(print());

		verify(favService, times(1)).saveMatchToFavorite(any(), eq(user.getUserId()));
	}

	@Test
	public void testAddMatchToFavoriteListFailure() throws Exception {
		when(favService.saveMatchToFavorite(any(), eq(user.getUserId()))).thenThrow(MatchAlreadyExistsException.class);
		mockMvc.perform(put("/favouriteservice/user/{userId}/addmatch", user.getUserId())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isConflict())
				.andDo(print());

		verify(favService, times(1)).saveMatchToFavorite(any(), eq(user.getUserId()));

	}

	@Test
	public void testGetAllTrackFromWishList() throws Exception {
		when(favService.getMatchList((user.getUserId()))).thenReturn(list);
		mockMvc.perform(get("/favouriteservice/user/{userId}/getmatchlist", user.getUserId())
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(match))).andExpect(status().isOk())
				.andDo(print());

		verify(favService, times(1)).getMatchList(user.getUserId());

	}

	private static String jsonToString(final Object obj) throws Exception {
		String result;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (Exception e) {
			result = "Error in Json processing....!!";
		}
		return result;
	}

}
