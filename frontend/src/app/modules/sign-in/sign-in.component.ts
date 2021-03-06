import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StorageService } from './../../services/storage.service';
import { Token } from './../../classes/token';
import { SignInService } from './../../services/sign-in.service';
import { Subscription } from 'rxjs';
import { LoginModel } from './../../classes/login-model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  private subscription: Subscription[] = [];

  // tslint:disable-next-line: no-inferrable-types
  public hide: boolean = true;

  constructor(public dialog: MatDialogRef<SignInComponent>,
              private signInService: SignInService,
              private snackBar: MatSnackBar,
  ) { }

  public formLogin: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z0-9]{6,45}')])
  });

  ngOnInit() {
  }

  public generateToken(loginUser: LoginModel): void {
    this.subscription.push(this.signInService.generateToken(loginUser));
  }

  public onSubmit(): void {
    if (this.formLogin.valid) {
      this.generateToken(this.formLogin.value);
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, {duration: time});
  }

  public close(): void {
     this.dialog.close();
  }

  public toggle(): void {
    this.hide = !this.hide;
  }

}
