import { Subscription } from 'rxjs';
import { PlaceService } from './../../services/place.service';
import { PlaceModel } from './../../classes/place-model';
import { Component, OnInit, Inject, OnDestroy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-palce',
  templateUrl: './palce.component.html',
  styleUrls: ['./palce.component.css']
})
export class PalceComponent implements OnInit, OnDestroy {

  public placeModels: PlaceModel[] = [];
  private subscription: Subscription[] = [];

  public rows: number[] = [];

  constructor(private placeService: PlaceService,
              public dialog: MatDialogRef<PalceComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any
              ) { }

  ngOnInit() {
    this.getAllPlaceModelsBySeance();
    for (let i = 1; i <= this.data.seance.hall.rows; i++) {
      this.rows.push(i);
    }
  }

  private getAllPlaceModelsBySeance(): void {
    this.subscription.push(this.placeService.getAllPlaceModelsBySeance(this.data.seance.idSeance)
      .subscribe(arg => this.placeModels = arg)
    );
  }

  public closeDialog(): void {
    this.dialog.close();
  }

  logPlace(place: PlaceModel) {
    console.log(place);
  }

  ngOnDestroy() {

  }

}
