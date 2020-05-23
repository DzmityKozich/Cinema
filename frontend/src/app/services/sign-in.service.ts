import { UserModel } from './../classes/user-model';
import { StorageService } from './storage.service';
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
  private currentUser: UserModel;

  constructor(private http: HttpClient,
              private storage: StorageService
  ) { }

  public login(login: LoginModel): Observable<Token> {
    return this.http.post(this.path, login);
  }

  public reLogin(): Promise<any> {
    const token = this.storage.getToken();
    if (token !== null) {
      const promise = this.http.post<any>(this.path + '/relogin', token).toPromise();
      promise.then(data => this.currentUser = data)
        .catch((err: any) => {
            this.storage.clearStorage();
            window.location.reload();
          }
        );
      return promise;
    } else {
      return null;
    }
  }

  public getCurrentUser(): any {
    return this.currentUser;
  }
}
