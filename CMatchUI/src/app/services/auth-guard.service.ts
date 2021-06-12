import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { TOKEN_NAME } from '../login/login.component';

@Injectable({
  providedIn: 'root'
})

export class AuthGuardService {

  constructor(private router: Router) { }

  canActivate() {
    if (localStorage.getItem(TOKEN_NAME) && sessionStorage.getItem("username")) {
      console.log('in can activate');
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
}
