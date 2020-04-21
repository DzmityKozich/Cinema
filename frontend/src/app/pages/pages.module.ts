import { ShareModule } from './../share/share.module';
import { RouterModule } from '@angular/router';
import { ModulesModule } from './../modules/modules.module';
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
import { MatCardModule } from '@angular/material/card';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { TextFieldModule } from '@angular/cdk/text-field';

@NgModule({
  declarations: [
    HomeComponent,
    MovieComponent,
    NotFoundComponent,
    AllMoviesComponent,
    AllCinemasComponent,
    CinemaComponent,
    AdminPageComponent,
  ],
  imports: [
    CommonModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ModulesModule,
    ShareModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,

    MatButtonModule,
    MatPaginatorModule,
    MatCardModule,
    MatInputModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatSelectModule,
    TextFieldModule
  ],
  exports: [HomeComponent]
})
export class PagesModule { }
