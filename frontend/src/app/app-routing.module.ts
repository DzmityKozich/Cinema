import { AdminPageComponent } from './pages/admin-page/admin-page.component';
import { AllCinemasComponent } from './pages/all-cinemas/all-cinemas.component';
import { AllMoviesComponent } from './pages/all-movies/all-movies.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { PagesModule } from './pages/pages.module';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieComponent } from './pages/movie/movie.component';
import { CinemaComponent } from './pages/cinema/cinema.component';
import { UserAccountComponent } from './pages/user-account/user-account.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'movies', component: AllMoviesComponent },
  { path: 'movies/:id', component: MovieComponent },
  { path: 'cinemas', component: AllCinemasComponent },
  { path: 'cinemas/:id', component: CinemaComponent },
  { path: 'admin', component: AdminPageComponent },
  { path: 'user', component: UserAccountComponent},

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PagesModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
