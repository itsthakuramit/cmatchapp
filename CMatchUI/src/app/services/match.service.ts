import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Match } from '../models/match';
import { User } from '../models/user';
export const USER_NAME = "username";

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  matchApi : string ;
  apiKey : string ;
  loginEndPoint : string;
  registerEndPoint : string;
  favouriteEndPoint : string;
  recommendedEndPoint : string;
  username : string; 

  constructor(private httpClient : HttpClient, private router : Router) { 
    this.matchApi = 'https://cricapi.com/api/matchCalendar?';
    this.apiKey = 'zVKw4RPaqoguWVU242lK8pWVzy33';
    this.loginEndPoint = 'http://localhost:8094/userservice/user/login';
    this.registerEndPoint = 'http://localhost:8094/userservice/user/register';
    this.favouriteEndPoint = 'http://localhost:8094/favouriteservice/favouriteservice';
    this.recommendedEndPoint = 'http://localhost:8094/recommendationservice/recommendation';
  }

  loginUser(newUser: any) {
    const url = this.loginEndPoint;
    sessionStorage.setItem(USER_NAME, newUser.userId);
    return this.httpClient.post(url, newUser);
  }

  registerUser(newUser: any) {
    const url = this.registerEndPoint;
    return this.httpClient.post(url, newUser);
  }


  logout() {
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
    localStorage.removeItem("token_name");
    localStorage.clear();
  }


  getNewMatches() : Observable<any>{
    this.matchApi = 'https://cricapi.com/api/matches';
    this.apiKey = '?apikey=zVKw4RPaqoguWVU242lK8pWVzy33';   
    const url = `${this.matchApi}${this.apiKey}`;
    return this.httpClient.get(url);
  }


  getOldMatches() : Observable<any>{
    this.matchApi = 'https://cricapi.com/api/cricket';
    this.apiKey = '?apikey=zVKw4RPaqoguWVU242lK8pWVzy33';   
    const url = `${this.matchApi}${this.apiKey}`;
    return this.httpClient.get(url);
  }

  
  getMatchCalender() : Observable<any>{
    this.matchApi = 'https://cricapi.com/api/matchCalendar';
    this.apiKey = '?apikey=zVKw4RPaqoguWVU242lK8pWVzy33';   
    const url = `${this.matchApi}${this.apiKey}`;
    return this.httpClient.get(url);
  }


  addToFavourite(match: Match){
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.favouriteEndPoint + "/user/" + this.username + "/addmatch";
    return this.httpClient.put(url, match);
  }


  removeFromFavourite(match : Match) : any{
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.favouriteEndPoint + "/user/" + this.username + "/deletematch";
    return this.httpClient.put(url, match);
  }


  getFavouriteMatches() :  Observable<any>{
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.favouriteEndPoint + "/user/" + this.username + "/getmatchlist";
    return this.httpClient.get(url);
  }


  addToRecommend(match : Match) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.recommendedEndPoint + "/user/" + this.username + "/addmatch";
    return this.httpClient.put(url, match);
  }


  getRecommendMatches() : Observable<any> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.recommendedEndPoint + "/user/" + this.username + "/getmatchlist";
    return this.httpClient.get(url);
  }

  removefromrecommend(match : Match): any{
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.recommendedEndPoint + "/user/" + this.username + "/deletematch";
    return this.httpClient.put(url, match);
  }

}

