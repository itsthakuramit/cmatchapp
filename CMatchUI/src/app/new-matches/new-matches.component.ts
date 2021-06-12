import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Match } from '../models/match';
import { MatchService } from '../services/match.service';


@Component({
  selector: 'app-new-matches',
  templateUrl: './new-matches.component.html',
  styleUrls: ['./new-matches.component.css']
})
export class NewMatchesComponent implements OnInit {
  newMatches: Array<Match> = [];
  id: number;
  matchObj: Match;
  isFav: boolean = false;
  matchList: Array<Match> = [];


  constructor(private matchService: MatchService, private router: Router) {
    this.newMatches = []
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('token') != null) {
      this.getAllMatches();
    }
    else {
      this.router.navigate(['/login']);
    }
  }


  getAllMatches() {
    this.matchService.getNewMatches()
      .subscribe(match => {
        const data = match['matches'];

        this.id = 0;
        data.forEach(element => {
          this.id++;
          this.matchObj = new Match();

          this.matchObj.matchId = element["unique_id"];
          this.matchObj.date = element["date"];
          this.matchObj.dateTimeGMT = element["dateTimeGMT"];
          this.matchObj.team_1 = element["team-1"];
          this.matchObj.team_2 = element["team-2"];
          this.matchObj.type = element["type"];
          this.matchObj.squad = element["squad"];
          this.matchObj.toss_winner_team = element["toss_winner_team"];
          this.matchObj.winner_team = element["winner_team"];
          this.matchObj.matchStarted = element["matchStarted"];

          this.matchObj.date = new Date().toISOString().slice(0,10);

          this.newMatches.push(this.matchObj);
        });
      });
  }


  addToWatchlist(match: Match) {
    this.matchService.addToFavourite(match).subscribe(
      data => {
        console.log(data)
        alert("Added successfully..");
        // this.getAllMatches();
      }, 
      error => {
        console.log(error);

      })
      
    
    match.isfavorite = true;
  }

  searchMatches(searchForm: any): any {
    this.newMatches = [];
    this.matchService.getNewMatches()
      .subscribe(match => {
        const data = match['matches'];

        this.id = 0;
        data.forEach(element => {
          this.id++;
          this.matchObj = new Match();

          this.matchObj.matchId = element["unique_id"];
          this.matchObj.date = element["date"];
          this.matchObj.dateTimeGMT = element["dateTimeGMT"];
          this.matchObj.team_1 = element["team-1"];
          this.matchObj.team_2 = element["team-2"];
          this.matchObj.type = element["type"];
          this.matchObj.squad = element["squad"];
          this.matchObj.toss_winner_team = element["toss_winner_team"];
          this.matchObj.winner_team = element["winner_team"];
          this.matchObj.matchStarted = element["matchStarted"];

          this.matchObj.date = new Date().toISOString().slice(0,10);

          if(this.matchObj.type === searchForm.search || this.matchObj.team_1 === searchForm.search || this.matchObj.team_2 === searchForm.search){
            this.newMatches.push(this.matchObj);
            this.matchService.addToRecommend(this.matchObj).subscribe(data =>
              {
                console.log(data);
              });
          }
        });
    });
}


  logout() {
    this.matchService.logout();
    this.router.navigate(['/dashboard']);
  }

}

