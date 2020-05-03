import { BillingService } from './../../services/billing.service';
import { BillingModel } from './../../classes/billing-model';
import { UserModel } from './../../classes/user-model';
import { Subscription } from 'rxjs';
import { PlaceService } from './../../services/place.service';
import { PlaceModel } from './../../classes/place-model';
import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { tick } from '@angular/core/testing';

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

  public rows: number[] = [];
  private sum = 0;
  private price: number;

  constructor(private placeService: PlaceService,
              private billingService: BillingService,
              private snackBar: MatSnackBar,
              public dialog: MatDialogRef<PalceComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.getAllPlaceModelsBySeance();
    this.getBillingModelById();
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
          this.closeDialog();
        }
        )
    );
  }

  // сделать так, чтобы при выборе место одним человеком его не мог выбрать другой
  // ввести новый статус
  // сделать setUnterval
  public selectPlace(place: PlaceModel) {
    const isPlaceSelected: boolean = this.selectedPlaceModels.indexOf(place) === -1;
    const isLength: boolean =  this.selectedPlaceModels.length === 0;
    if (place.state === 'Vacancy') {
      if (isPlaceSelected || isLength) {
        document.getElementById(place.idPlace.toString()).classList.add('place--select');
        this.selectedPlaceModels.push(place);
        this.sum += this.price;
        console.log(this.selectedPlaceModels);
      } else {
        document.getElementById(place.idPlace.toString()).classList.remove('place--select');
        this.selectedPlaceModels.splice(this.selectedPlaceModels.indexOf(place), 1);
        this.sum -= this.price;
      }
    }
  }

  private clearSelectedPlaceModels(): void {
    this.selectedPlaceModels = [];
  }

  public buyTicket(): void {
    if (this.billingModel.balance >= this.sum) {
      this.selectedPlaceModels.forEach(place => place.billing = this.billingModel);
      this.takePlaces(this.selectedPlaceModels);
    } else {
      this.openSnackBar('You do not have enough money', 'Ok', 2500);
    }
  }

  private getBillingModelById(): void {
    this.subscription.push(this.billingService.getBillingModelById()
      .subscribe(arg => this.billingModel = arg)
    );
  }

  public closeDialog(): void {
    this.dialog.close();
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
  }

}
