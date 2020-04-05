import { HeaderModule } from './../header/header.module';
import { NavbarModule } from './../navbar/navbar.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';

@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    NavbarModule,
    HeaderModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
  ],
  exports: [HomeComponent]
})
export class PagesModule { }
