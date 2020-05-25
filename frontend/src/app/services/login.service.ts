import { StorageService } from './storage.service';
import { Observable } from 'rxjs';
import { LoginModel } from './../classes/login-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly path = '/api/logins';

  constructor(private http: HttpClient,
              private storage: StorageService
  ) { }

  public saveLoginModel(login: LoginModel): Observable<LoginModel> {
    return this.http.post<LoginModel>(this.path, login);
  }

  public validator(login: string): Observable<boolean> {
    return this.http.get<boolean>(this.path + '/login/' + login);
  }

  public getAllLoginModels(): Observable<LoginModel[]> {
    return this.http.get<LoginModel[]>(this.path);
  }
}
