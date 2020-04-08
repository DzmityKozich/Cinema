import { NotFoundComponent } from './pages/not-found/not-found.component';
import { PagesModule } from './pages/pages.module';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieComponent } from './pages/movie/movie.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'movies/:id', component: MovieComponent },

  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PagesModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
