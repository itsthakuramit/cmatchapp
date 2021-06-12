import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Oldmatch } from '../models/oldmatch';
import { MatchService } from '../services/match.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-old-matches',
  templateUrl: './old-matches.component.html',
  styleUrls: ['./old-matches.component.css']
})
export class OldMatchesComponent implements OnInit {

  oldMatches: Array<Oldmatch> = [];
  id: number;
  oldmatch: Oldmatch;
  errorMessage: string

  constructor(private matchService: MatchService, private router: Router, private userService: UserService) {
    this.oldMatches = [];
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('token') != null) {
      this.matchService.getOldMatches()
        .subscribe(old => {
          const data = old['data'];

          this.id = 0;
          data.forEach(element => {

            this.id++;
            this.oldmatch = new Oldmatch();

            this.oldmatch.id = element["unique_id"];
            this.oldmatch.description = element["description"];

            this.oldMatches.push(this.oldmatch);
          });
        });

    }
    else {
      this.router.navigate(["/login"]);
    }

  }


  logout() {
    this.matchService.logout();
    this.router.navigate(['/dashboard']);
  }
}
