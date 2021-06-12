import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { MatchService} from '../services/match.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User;
  constructor(private matchService : MatchService, private router: Router) {
      this.user= new User();
     }

  ngOnInit() {
    if (sessionStorage.getItem('token') != null) {
      this.router.navigate(["/dashboard"]);
    }
    else {
      this.router.navigate(["/register"]);
  }
  }

  register() {
    this.matchService.registerUser(this.user)
      .subscribe(data => {
        this.router.navigate(["/login"]);
        alert('User Registered Succesfully...');
      },
        error => {
          alert('Error..Please check your credentials..!!');
          console.log(error);
        })
  }

}
