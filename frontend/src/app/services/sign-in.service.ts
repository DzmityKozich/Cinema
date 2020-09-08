import { MatSnackBar } from '@angular/material/snack-bar';
import { UserModel } from './../classes/user-model';
import { StorageService } from './storage.service';
import { LoginModel } from './../classes/login-model';
import { Token } from './../classes/token';
import { Observable, Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  // tslint:disable-next-line: no-inferrable-types
  private path: string = '/api/login';
  private currentUser: UserModel;
  private token: Token = new Token();

  constructor(private http: HttpClient,
              private storage: StorageService,
              private snackBar: MatSnackBar
  ) { }

  public login(login: LoginModel): Observable<Token> {
    return this.http.post(this.path, login);
  }

  public generateToken(loginUser: LoginModel): Subscription {
    return this.login(loginUser).subscribe(
      arg => {
        this.token = arg;
        this.storage.setToken(this.token);
      },
      (err) => {
          this.snackBar.open('Check your email and password!', 'Ok', {duration: 1500});
          console.log(err);
      },
      () => {
        this.snackBar.open('Complited successfully!', 'Ok', {duration: 2000});
        window.location.reload();
      }
    );
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

  public refreshToken(): any {
    const jwtRefreshToken = {
      refreshToken: this.storage.getRefreshToken(),
      userModel: this.currentUser
    };
    return this.http.post(this.path + '/refresh-token/', jwtRefreshToken).pipe(
      tap((arg: Token) => {
        this.storage.clearStorage();
        this.storage.setToken(arg);
      })
    );
  }

  public deleteRefreshToken(): Observable<void> {
    return this.http.delete<void>('/api/refresh-token/' + this.storage.getRefreshToken());
  }

  public getCurrentUser(): any {
    return this.currentUser;
  }
}
