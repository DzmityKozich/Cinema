import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { StorageService } from './../../services/storage.service';
import { BillingModel } from './../../classes/billing-model';
import { BillingService } from './../../services/billing.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { UserModel } from 'src/app/classes/user-model';

@Component({
  selector: 'app-put-money',
  templateUrl: './put-money.component.html',
  styleUrls: ['./put-money.component.css']
})
export class PutMoneyComponent implements OnInit {

  public billingModel: BillingModel = new BillingModel();
  private currentUser: UserModel = new UserModel();
  private subscription: Subscription[] = [];

  constructor(public dialog: MatDialogRef<PutMoneyComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private billingService: BillingService,
              private storage: StorageService,
              private snackBar: MatSnackBar
  ) { }

  public form: FormGroup = new FormGroup({
    amount: new FormControl('', [Validators.required, Validators.pattern('[0-9]{1,10}(\.[0-9]{1,2})?')])
  });

  ngOnInit() {
    this.currentUser = this.storage.getCurrentUser();
  }

  public putMoney(): void {
    this.subscription.push(this.billingService.putMoney(this.currentUser.idUser, this.data.money.amount)
      .subscribe(
        () => {
          this.openSnackBar('Success', 'Ok', 1500);
          this.closeDialog(this.data.money.amount);
         }
        )
    );
  }

  public onSubmit(): void {
    if (this.form.valid) {
      this.data.money = this.form.value;
      this.putMoney();
    }
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, {duration: time});
  }

  public closeDialog(value?: any): void {
    this.dialog.close(value);
  }

}
