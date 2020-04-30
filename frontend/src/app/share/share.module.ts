import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './button/button.component';
import {MatButtonModule} from '@angular/material/button';
import { DatepickerComponent } from './datepicker/datepicker.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { enGbLocale } from 'ngx-bootstrap/locale';

defineLocale('engb', enGbLocale);

@NgModule({
  declarations: [ButtonComponent, DatepickerComponent],
  imports: [
    CommonModule,
    MatButtonModule,

    BsDatepickerModule.forRoot(),
  ],
  exports: [
    ButtonComponent,
    DatepickerComponent
  ]
})
export class ShareModule { }
