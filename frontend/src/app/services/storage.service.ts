import { Token } from '../classes/token';
import { Injectable } from '@angular/core';
import { UserModel } from '../classes/user-model';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private readonly TOKEN = 'token';
  private readonly REFRESH_TOKEN = 'refreshToken';

  constructor() { }

  public setToken(token: Token): void {
    localStorage.setItem(this.TOKEN, token.token);
    localStorage.setItem(this.REFRESH_TOKEN, token.refreshToken);
  }

  public getToken(): string {
    return localStorage.getItem(this.TOKEN);
  }

  public getRefreshToken(): string {
    return localStorage.getItem(this.REFRESH_TOKEN);
  }

  public clearStorage(): void {
    localStorage.removeItem(this.TOKEN);
    localStorage.removeItem(this.REFRESH_TOKEN);
  }
}
