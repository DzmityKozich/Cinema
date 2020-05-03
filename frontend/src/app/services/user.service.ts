import { Observable } from 'rxjs';
import { UserModel } from './../classes/user-model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private readonly path = '/api/users';

  constructor(private http: HttpClient) { }

  public saveUserModel(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(this.path, user);
  }
}
