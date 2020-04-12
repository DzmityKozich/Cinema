import { RouterModule } from '@angular/router';
import { ModulesModule } from './../modules/modules.module';
import { HeaderModule } from './../header/header.module';
import { NavbarModule } from './../navbar/navbar.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { MovieComponent } from './movie/movie.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { MatButtonModule } from '@angular/material/button';
import { AllMoviesComponent } from './all-movies/all-movies.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AllCinemasComponent } from './all-cinemas/all-cinemas.component';
import { CinemaComponent } from './cinema/cinema.component';

@NgModule({
  declarations: [HomeComponent, MovieComponent, NotFoundComponent, AllMoviesComponent, AllCinemasComponent, CinemaComponent],
  imports: [
    CommonModule,
    NavbarModule,
    HeaderModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ModulesModule,
    RouterModule,
    MatButtonModule,
    MatPaginatorModule
  ],
  exports: [HomeComponent]
})
export class PagesModule { }
