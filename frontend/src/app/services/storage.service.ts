import { Token } from '../classes/token';
import { Injectable } from '@angular/core';
import { UserModel } from '../classes/user-model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  public currentUser: UserModel = new UserModel();

  private readonly TOKEN = 'token';
  private readonly CURRENT_USER = 'currentUser';

  constructor() { }

  public setToken(token: Token): void {
    localStorage.setItem(this.TOKEN, token.token);
    this.currentUser = token.currentUser;
    localStorage.setItem(this.CURRENT_USER, JSON.stringify(token.currentUser));
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN);
  }

  public getCurrentUser(): UserModel {
    // return this.currentUser;
    return JSON.parse(localStorage.getItem(this.CURRENT_USER));
  }

  public clearStorage(): void {
    localStorage.removeItem(this.TOKEN);
    this.currentUser = null;
    localStorage.removeItem(this.CURRENT_USER);
  }
}
