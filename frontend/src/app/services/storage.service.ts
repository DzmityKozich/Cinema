import { Token } from '../classes/token';
import { Injectable } from '@angular/core';
import { UserModel } from '../classes/user-model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  public currentUser: UserModel = new UserModel();

  private readonly TOKEN = 'token';

  constructor() { }

  public setToken(token: Token): void {
    localStorage.setItem(this.TOKEN, token.token);
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN);
  }

  public clearStorage(): void {
    localStorage.removeItem(this.TOKEN);
  }
}
