import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { MatchService, USER_NAME} from '../services/match.service';
import { UserService } from '../services/user.service';
export const TOKEN_NAME = "token_name";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;

  constructor(private matchService: MatchService, private router: Router, private userService :UserService) {
    this.user = new User();
  }

  ngOnInit() {
    if (sessionStorage.getItem('token') != null) {
      this.router.navigate(["/dashboard"]);
    }
    else {
      this.router.navigate(["/login"]);
  }
}

  login() {
    this.matchService.loginUser(this.user).subscribe(data => {
      if (data) {
        localStorage.setItem('token', data["token"]);
        sessionStorage.setItem("token", data['token']);
        this.router.navigate(["/dashboard"]);
      }
    },
      error => {
          alert('Invalid Credentials...');
        }
    )
  }
}
