import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FavouritesComponent } from './favourites/favourites.component';
import { LoginComponent } from './login/login.component';
import { NewMatchesComponent } from './new-matches/new-matches.component';
import { OldMatchesComponent } from './old-matches/old-matches.component';
import { RecommendationsComponent } from './recommendations/recommendations.component';
import { RegisterComponent } from './register/register.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'new-matches', component: NewMatchesComponent },
  { path: 'old-matches', component: OldMatchesComponent },
  { path: 'favourites', component: FavouritesComponent},
  { path: 'recommendation', component: RecommendationsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
