import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  private readonly TOKEN = 'token';

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem(this.TOKEN);

    const reqClone = req.clone({
      headers: new HttpHeaders(token && token !== 'null' ? {
        Authorization: 'Bearer_' + token
      } : null)
    });

    return next.handle(reqClone);
  }
}
