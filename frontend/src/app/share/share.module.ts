import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from './button/button.component';
import {MatButtonModule} from '@angular/material/button';
import { DatepickerComponent } from './datepicker/datepicker.component';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { enGbLocale } from 'ngx-bootstrap/locale';
import { AnimationComponent } from './animation/animation.component';

defineLocale('engb', enGbLocale);

@NgModule({
  declarations: [ButtonComponent, DatepickerComponent, AnimationComponent],
  imports: [
    CommonModule,
    BrowserAnimationsModule,

    MatButtonModule,

    BsDatepickerModule.forRoot(),
  ],
  exports: [
    ButtonComponent,
    DatepickerComponent,
    AnimationComponent
  ]
})
export class ShareModule { }
