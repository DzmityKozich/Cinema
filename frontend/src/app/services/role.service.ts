import { StorageService } from './storage.service';
import { Injectable } from '@angular/core';
import { UserModel } from '../classes/user-model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private currentUser: UserModel = new UserModel();

  constructor(private storage: StorageService) {
    this.currentUser = this.storage.getCurrentUser();
   }

  public isAuthorized(): boolean {
    return this.currentUser !== null;
  }

  public isUser(): boolean {
    if (!this.isAuthorized()) {
      return false;
    }
    return this.currentUser.role === 'USER';
  }

  public isAdmin(): boolean {
    if (!this.isAuthorized()) {
      return false;
    }
    return this.currentUser.role === 'ADMIN';
  }
}
