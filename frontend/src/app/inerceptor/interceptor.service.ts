import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, BehaviorSubject } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { SignInService } from '../services/sign-in.service';
import { Token } from '../classes/token';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  private readonly TOKEN = 'token';
  isTokenRefreshing = false;

  constructor(private signInService: SignInService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(this.TOKEN);

    if (req.url.indexOf('refresh-token') !== -1) {
      return next.handle(req);
    }

    return next.handle(this.setHeader(req, token)).pipe(
      catchError(err => {
        if (err instanceof HttpErrorResponse
          && err.status === 500) {
          return this.handelError(req, next);
        } else {
          return throwError(err);
        }
      })
    );

  }

  private handelError(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isTokenRefreshing) {
      this.isTokenRefreshing = true;

      return this.signInService.refreshToken().pipe(
        switchMap((jwtRefreshToken: Token) => {
          this.isTokenRefreshing = false;
          return next.handle(this.setHeader(req, jwtRefreshToken.token));
        })
      );
    }
  }

  private setHeader(req: HttpRequest<any>, token: string) {
    return req.clone({
      headers: new HttpHeaders(token && token !== 'null' ? {
        Authorization: 'Bearer_' + token
      } : null)
    });
  }
}
