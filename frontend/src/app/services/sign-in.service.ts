import { LoginModel } from './../classes/login-model';
import { Token } from './../classes/token';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  // tslint:disable-next-line: no-inferrable-types
  private path: string = '/api/login';

  constructor(private http: HttpClient) { }

  public login(login: LoginModel): Observable<Token> {
    return this.http.post(this.path, login);
  }
}
