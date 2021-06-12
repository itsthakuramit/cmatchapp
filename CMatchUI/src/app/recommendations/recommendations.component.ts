import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Match } from '../models/match';
import { MatchService } from '../services/match.service';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css']
})
export class RecommendationsComponent implements OnInit {
  match : Match;
  matchList : Array<Match> = []

  constructor(private matchService : MatchService, private router : Router) { 
    this.matchList = [];
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('token') != null) {
      this.getAllMatches();
    }
    else {
      this.router.navigate(['/login']);
    }
  }

  getAllMatches() : any{
    this.matchService.getRecommendMatches().subscribe(
      (data)=>{
          this.matchList = data;        
      });
  }

  removeFromRecommend(match: Match): any{

    this.matchService.removefromrecommend(match).subscribe(
      (data) => {
        this.matchList = this.getAllMatches();
      });
      window.location.reload();
    }
  
    
  logout() {
    this.matchService.logout();
   this.router.navigate(['/dashboard']);
 }
}
