package com.hcl.recommendation.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
@Component
public class Recommend {

	@Id
	private String userId;
	private List<Match> matchList;

	public Recommend() {
		super();
	}

	public Recommend(String userId, List<Match> matchList) {
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
