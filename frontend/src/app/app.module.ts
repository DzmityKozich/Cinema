import { SignInService } from './services/sign-in.service';
import { InterceptorService } from './inerceptor/interceptor.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BsDatepickerModule } from 'ngx-bootstrap/datepicker';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    HttpClientModule,
  ],
  providers: [InterceptorService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    },
    SignInService,
    {
      provide: APP_INITIALIZER,
      useFactory: (signInService: SignInService) => () => signInService.reLogin(),
      deps: [SignInService],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
