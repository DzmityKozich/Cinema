import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { SignUpComponent } from './sign-up/sign-up.component';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [FooterComponent,
    HeaderComponent,
    NavbarComponent,
    PalceComponent,
    SignUpComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ShareModule,
    FormsModule,
    ReactiveFormsModule,

    MatToolbarModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  exports: [
    FooterComponent,
    HeaderComponent,
    NavbarComponent
  ],
  entryComponents: [
    SignUpComponent
  ]
})
export class ModulesModule { }
