import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Match } from '../models/match';
import { MatchService } from '../services/match.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  match : Match;
  matchList : Array<Match> = []
  errorMessage : string

  constructor(private matchService : MatchService, private router : Router, private userService : UserService) { 
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
    this.matchService.getFavouriteMatches().subscribe(
      (data)=>{
          this.matchList = data;
         
      });
  }

  removefromfavorite(match: Match): any{

    this.matchService.removeFromFavourite(match).subscribe(
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
