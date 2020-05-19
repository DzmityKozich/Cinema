import { SignInService } from './sign-in.service';
import { StorageService } from './storage.service';
import { Injectable } from '@angular/core';
import { UserModel } from '../classes/user-model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private currentUser: UserModel = new UserModel();

  constructor(private storage: StorageService,
              private signInService: SignInService
  ) {
    this.currentUser = this.signInService.getCurrentUser();
  }

  public isAuthorized(): boolean {
    return !!this.storage.getToken();
  }

  public isUser(): boolean {
    if (!this.isAuthorized()) {
      return false;
    }
    return this.currentUser.role === 'ROLE_USER';
  }

  public isAdmin(): boolean {
    if (!this.isAuthorized()) {
      return false;
    }
    return this.currentUser.role === 'ROLE_ADMIN';
  }
}
