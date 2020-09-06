import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, switchMap } from 'rxjs/operators';
import { SignInService } from '../services/sign-in.service';
import { Token } from '../classes/token';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  private readonly TOKEN = 'token';

  constructor(private signInService: SignInService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(this.TOKEN);

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
    return this.signInService.refreshToken().pipe(
      switchMap((jwtRefreshToken: Token) => {
        return next.handle(this.setHeader(req, jwtRefreshToken.token));
      })
    );
  }

  private setHeader(req: HttpRequest<any>, token: string) {
    return req.clone({
      headers: new HttpHeaders(token && token !== 'null' ? {
        Authorization: 'Bearer_' + token
      } : null)
    });
  }
}
