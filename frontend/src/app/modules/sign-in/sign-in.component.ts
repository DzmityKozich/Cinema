import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  // tslint:disable-next-line: no-inferrable-types
  public hide: boolean = true;

  constructor(public dialog: MatDialogRef<SignInComponent>) { }

  public formLogin: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern('[A-Za-z0-9]{6,45}')])
  });

  ngOnInit() {
  }

  public onSubmit(): void {
    console.log(this.formLogin.value);
  }

  public close(): void {
     this.dialog.close();
  }

  public toggle(): void {
    this.hide = !this.hide;
  }

}
