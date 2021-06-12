package com.hcl.favourite.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	
	@Id
	private String userId;
	private List<Match> matchList;
	
	public User() {
		super();
	}

	public User(String userId, List<Match> matchList) {
		super();
		this.userId = userId;
		this.matchList = matchList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
}
