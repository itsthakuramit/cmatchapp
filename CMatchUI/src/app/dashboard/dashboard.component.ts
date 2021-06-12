import { elementEventFullName } from '@angular/compiler/src/view_compiler/view_compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Calender } from '../models/calender';
import { Match } from '../models/match';
import { MatchService } from '../services/match.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  calMatches: Array<Calender> = [];
  id: number;
  calObj: Calender
  check: boolean = false


  constructor(private matchService: MatchService, private router: Router, private userService: UserService) {
    this.calMatches = []
  }

  ngOnInit(): void {
    if (sessionStorage.getItem('token') != null) {
      this.getMatchCalender();
      this.check = true;
    }
    else {
      this.getMatchCalender();
    }
  }

  getMatchCalender(): any {
    this.matchService.getMatchCalender()
      .subscribe(cal => {
        const data = cal['data'];

        this.id = 0;
        data.forEach(element => {

          this.id++;
          this.calObj = new Calender();

          this.calObj.id = element['unique_id'];
          this.calObj.name = element['name'];
          this.calObj.date = element['date'];
          this.calMatches.push(this.calObj);
        });
      });
  }


  logout() {
    this.matchService.logout();
    window.location.reload();
    this.router.navigate(['/dashboard']);
  }

}