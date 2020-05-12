import { StorageService } from './../../services/storage.service';
import { SignInComponent } from './../sign-in/sign-in.component';
import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { SignUpComponent } from '../sign-up/sign-up.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public matDialog: MatDialog,
              private storage: StorageService
  ) { }

  ngOnInit() {
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
    this.storage.clearStorage();
    window.location.reload();
  }

}
