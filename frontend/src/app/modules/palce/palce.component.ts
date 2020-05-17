import { StorageService } from './../../services/storage.service';
import { BillingService } from './../../services/billing.service';
import { BillingModel } from './../../classes/billing-model';
import { UserModel } from './../../classes/user-model';
import { Subscription } from 'rxjs';
import { PlaceService } from './../../services/place.service';
import { PlaceModel } from './../../classes/place-model';
import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-palce',
  templateUrl: './palce.component.html',
  styleUrls: ['./palce.component.css']
})
export class PalceComponent implements OnInit, OnDestroy {

  public placeModels: PlaceModel[] = [];
  private subscription: Subscription[] = [];
  private selectedPlaceModels: PlaceModel[] = [];
  private billingModel: BillingModel = new BillingModel();
  private currentUser: UserModel = new UserModel();

  private timer = setInterval(() => this.getAllPlaceModelsBySeance(), 5000);
  public rows: number[] = [];
  private sum = 0;
  private price: number;

  constructor(private placeService: PlaceService,
              private billingService: BillingService,
              private snackBar: MatSnackBar,
              public dialog: MatDialogRef<PalceComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private storage: StorageService,
  ) { }

  ngOnInit() {
    this.currentUser = this.storage.getCurrentUser();
    this.getBillingModelByUser();
    this.getAllPlaceModelsBySeance();
    this.countRows();
    this.price = this.data.seance.price;
  }

  private getAllPlaceModelsBySeance(): void {
    this.subscription.push(this.placeService.getAllPlaceModelsBySeance(this.data.seance.idSeance)
      .subscribe(arg => this.placeModels = arg)
    );
  }

  private takePlaces(places: PlaceModel[]): void {
    this.subscription.push(this.placeService.takePlace(places)
      .subscribe(
        () => this.getAllPlaceModelsBySeance(),
        (err) => { },
        () => {
          this.openSnackBar('Success', 'Ok', 1500);
          this.dialog.close();
        }
      )
    );
  }

  private savePlace(place: PlaceModel): void {
    this.subscription.push(this.placeService.savePlaceModel(place)
      .subscribe(() => {
        this.getAllPlaceModelsBySeance();
      })
    );
  }

  private clearSelectedPlaceModels(places: PlaceModel[]): void {
    this.subscription.push(this.placeService.clearSelectedPlaceModels(places)
      .subscribe(
        () => { console.log('clear');
         },
      (err) => { },
      () => {
        this.dialog.close();
      }
      )
    );
  }

  private getBillingModelByUser(): void {
    this.subscription.push(this.billingService.getBillingModelByUser(this.currentUser.idUser)
      .subscribe(arg => {
        this.billingModel = arg;
      })
    );
  }

  public selectPlace(place: PlaceModel): void {
    if (place.state === 'Vacancy') {
      this.selectOn(place);
    } else if (this.isSelectedByMe(place)) {
      this.selectOff(place);
    }
  }

  private selectOn(place: PlaceModel): void {
    this.sum += this.price;
    place.state = 'Selected';
    place.billing = this.billingModel;
    this.selectedPlaceModels.push(place);
    this.savePlace(place);
  }

  private selectOff(place: PlaceModel): void {
    this.selectedPlaceModels.splice(this.selectedPlaceModels.indexOf(place), 1);
    this.sum -= this.price;
    this.clearSelectedPlace(place);
  }

  private clearSelectedPlace(place: PlaceModel): void {
    place.state = 'Vacancy';
    place.billing = null;
    this.savePlace(place);
  }

  public buyTicket(): void {
    if (this.billingModel.balance >= this.sum) {
      this.selectedPlaceModels.forEach(place => place.billing = this.billingModel);
      this.takePlaces(this.selectedPlaceModels);
    } else {
      this.openSnackBar('You do not have enough money', 'Ok', 2500);
    }
  }

  public isSelectedByMe(place: PlaceModel): boolean {
    if (place.state === 'Selected') {
      if (place.billing.idBilling === this.billingModel.idBilling) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public isVacancy(place: PlaceModel): boolean {
    if (place.state === 'Vacancy') {
      return true;
    } else {
      return false;
    }
  }

  public isTaken(place: PlaceModel): boolean {
    if (place.state === 'Taken') {
      return true;
    } else {
      return false;
    }
  }

  public isPlaceSelectedByOther(place: PlaceModel): boolean {
   if (place.state === 'Selected') {
     if (place.billing.idBilling !== this.billingModel.idBilling) {
       return true;
     } else {
       return false;
     }
   } else {
     return false;
   }
  }

  public closeDialog(): void {
    this.clearSelectedPlaceModels(this.selectedPlaceModels);
  }

  private countRows(): void {
    for (let i = 1; i <= this.data.seance.hall.rows; i++) {
      this.rows.push(i);
    }
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, { duration: time });
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
    clearTimeout(this.timer);
  }

}
