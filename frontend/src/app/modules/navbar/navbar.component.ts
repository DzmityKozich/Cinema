import { MatDialog } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { SignUpComponent } from '../sign-up/sign-up.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public matDialog: MatDialog) { }

  ngOnInit() {
  }

  public openSignUpDialog(): void {
    this.matDialog.open(SignUpComponent, {
      width: '500px',
      height: '407px'
    });
  }

}
