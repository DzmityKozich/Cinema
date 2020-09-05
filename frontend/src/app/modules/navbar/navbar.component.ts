import { RoleService } from './../../services/role.service';
import { StorageService } from './../../services/storage.service';
import { SignInComponent } from './../sign-in/sign-in.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { SignUpComponent } from '../sign-up/sign-up.component';
import { SignInService } from 'src/app/services/sign-in.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public isAuthorized: boolean;
  public isUser: boolean;
  public isAdmin: boolean;

  constructor(public matDialog: MatDialog,
              private storage: StorageService,
              private roleService: RoleService,
              private signInService: SignInService
  ) { }

  ngOnInit() {
    this.isAuthorized = this.roleService.isAuthorized();
    this.isUser = this.roleService.isUser();
    this.isAdmin = this.roleService.isAdmin();
  }

  public openSignInDialog(): void {
    this.matDialog.open(SignInComponent, {
      width: '',
      height: ''
    });
  }

  public openSignUpDialog(): void {
    this.matDialog.open(SignUpComponent, {
      width: '500px',
      height: '407px'
    });
  }

  public logout(): void {
    this.signInService.deleteRefreshToken().subscribe(
      () => this.storage.clearStorage()
    );
    window.location.reload();
  }

}
