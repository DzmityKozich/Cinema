import { ShareModule } from './../share/share.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { PalceComponent } from './palce/palce.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [FooterComponent,
    HeaderComponent,
    NavbarComponent,
    PalceComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ShareModule,

    MatToolbarModule,
    MatDialogModule
  ],
  exports: [
    FooterComponent,
    HeaderComponent,
    NavbarComponent
  ]
})
export class ModulesModule { }
