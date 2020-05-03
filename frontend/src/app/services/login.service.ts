import { Observable } from 'rxjs';
import { LoginModel } from './../classes/login-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly path = '/api/logins';

  constructor(private http: HttpClient) { }

  public saveLoginModel(login: LoginModel): Observable<LoginModel> {
    return this.http.post<LoginModel>(this.path, login);
  }
}
