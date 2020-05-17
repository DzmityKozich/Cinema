import { PlaceModel } from './../../classes/place-model';
import { MatDialog } from '@angular/material/dialog';
import { BillingModel } from './../../classes/billing-model';
import { BillingService } from './../../services/billing.service';
import { Subscription } from 'rxjs';
import { StorageService } from './../../services/storage.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserModel } from 'src/app/classes/user-model';
import { PutMoneyComponent } from 'src/app/modules/put-money/put-money.component';
import { PlaceService } from 'src/app/services/place.service';

@Component({
  selector: 'app-user-account',
  templateUrl: './user-account.component.html',
  styleUrls: ['./user-account.component.css']
})
export class UserAccountComponent implements OnInit, OnDestroy {

  public billingModel: BillingModel = new BillingModel();
  public currentUser: UserModel = new UserModel();
  public places: PlaceModel[] = [];
  private subscription: Subscription[] = [];

  public amount: number;

  constructor(private storage: StorageService,
              private billingService: BillingService,
              public matDialog: MatDialog,
              private placeService: PlaceService
  ) { }

  ngOnInit() {
    this.currentUser = this.storage.getCurrentUser();
    this.getBillingByUser();
    console.log(this.currentUser);
  }

  private getBillingByUser(): void {
    this.subscription.push(this.billingService.getBillingModelByUser(this.currentUser.idUser)
      .subscribe(
        arg => this.billingModel = arg,
        (err) => { },
        () => this.getAllPlaceModelsByBilling()
        )
    );
  }

  private getAllPlaceModelsByBilling(): void {
    this.subscription.push(this.placeService.getAllPlaceModelsByBilling(this.billingModel.idBilling)
      .subscribe(arg => this.places = arg)
    );
  }

  public openPutMoneyDialog(): void {
    const dialogRef = this.matDialog.open(PutMoneyComponent, {
      width: '',
      height: '',
      data: {money: this.amount}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result !== undefined) {
        this.getBillingByUser();
      }
    });
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
