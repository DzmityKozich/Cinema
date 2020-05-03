import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { UserModel } from './../../classes/user-model';
import { LoginService } from './../../services/login.service';
import { UserService } from './../../services/user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { LoginModel } from 'src/app/classes/login-model';
import { tick } from '@angular/core/testing';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  private userModel: UserModel = new UserModel();
  private loginModel: LoginModel = new LoginModel();
  private subscription: Subscription[] = [];

  // tslint:disable-next-line: no-inferrable-types
  public hide: boolean = true;

  constructor(private dialog: MatDialogRef<SignUpComponent>,
              private userService: UserService,
              private loginService: LoginService,
              private snackBar: MatSnackBar
  ) { }

  formUser: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$')]),
    lastName: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9]+$')])
  });

  formLogin: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z0-9]{6,45}')])
  });

  ngOnInit() {
  }

  public add() {
    if (this.formLogin.valid && this.formUser.valid) {
      this.userModel = this.formUser.value;
      this.saveUserModel();
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  public addUserModel(): void {
    console.log(this.formUser.value);
  }

  public addLoginModel(): void {
    console.log(this.formLogin.value);
  }

  private saveUserModel(): void {
    this.userModel.role = 'ROLE_USER';
    this.subscription.push(this.userService.saveUserModel(this.userModel)
      .subscribe(
        arg => {
          this.userModel = arg;
        },
        err => { },
        () => {
          console.log(this.userModel);
          this.loginModel = this.formLogin.value;
          this.loginModel.loginUser = this.userModel;
          this.saveLoginModel();
        }
        )
    );
  }

  private refreshUserModel(): void {
    this.userModel = new UserModel();
  }

  private saveLoginModel(): void {
    this.subscription.push(this.loginService.saveLoginModel(this.loginModel)
      .subscribe(
        () => {
          this.refreshUserModel();
          this.refreshLoginModel();
          this.openSnackBar('Success', 'Ok', 1500);
          this.closeDialog();
        }
      )
    );
  }

  public refreshLoginModel(): void {
    this.loginModel = new LoginModel();
  }

  public closeDialog(): void {
    this.dialog.close();
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, { duration: time });
  }

  public toggle(): void {
    this.hide = !this.hide;
  }

}
