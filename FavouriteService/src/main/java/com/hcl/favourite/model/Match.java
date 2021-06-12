package com.hcl.favourite.model;

public class Match {

	private Long matchId;
	private String date;
	private String dateTimeGMT;
	private String team_1;
	private String team_2;
	private String type;
	private String squad;
	private String toss_winner_team;
	private String winner_team;
	private String matchStarted;
	
	public Match() {
		super();
	}

	public Match(Long matchId, String date, String dateTimeGMT, String team_1, String team_2, String type, String squad,
			String toss_winner_team, String winner_team, String matchStarted) {

		this.matchId = matchId;
		this.date = date;
		this.dateTimeGMT = dateTimeGMT;
		this.team_1 = team_1;
		this.team_2 = team_2;
		this.type = type;
		this.squad = squad;
		this.toss_winner_team = toss_winner_team;
		this.winner_team = winner_team;
		this.matchStarted = matchStarted;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDateTimeGMT() {
		return dateTimeGMT;
	}

	public void setDateTimeGMT(String dateTimeGMT) {
		this.dateTimeGMT = dateTimeGMT;
	}

	public String getTeam_1() {
		return team_1;
	}

	public void setTeam_1(String team_1) {
		this.team_1 = team_1;
	}

	public String getTeam_2() {
		return team_2;
	}

	public void setTeam_2(String team_2) {
		this.team_2 = team_2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSquad() {
		return squad;
	}

	public void setSquad(String squad) {
		this.squad = squad;
	}

	public String getToss_winner_team() {
		return toss_winner_team;
	}

	public void setToss_winner_team(String toss_winner_team) {
		this.toss_winner_team = toss_winner_team;
	}

	public String getWinner_team() {
		return winner_team;
	}

	public void setWinner_team(String winner_team) {
		this.winner_team = winner_team;
	}

	public String getMatchStarted() {
		return matchStarted;
	}

	public void setMatchStarted(String matchStarted) {
		this.matchStarted = matchStarted;
	}
}
