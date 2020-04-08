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
import {MatButtonModule} from '@angular/material/button';

@NgModule({
  declarations: [HomeComponent, MovieComponent, NotFoundComponent],
  imports: [
    CommonModule,
    NavbarModule,
    HeaderModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ModulesModule,
    RouterModule,
    MatButtonModule
  ],
  exports: [HomeComponent]
})
export class PagesModule { }
