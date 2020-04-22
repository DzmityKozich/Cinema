import { PalceComponent } from './../../modules/palce/palce.component';
import { CinemaModel } from './../../classes/cinema-model';
import { SeanceModel } from './../../classes/seance-model';
import { SeanceService } from './../../services/seance.service';
import { MovieService } from './../../services/movie.service';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { MovieModel } from 'src/app/classes/movie-model';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit, OnDestroy {

  public movieModel: MovieModel = new MovieModel();
  public seances: SeanceModel[] = [];
  public cinemas: CinemaModel[] = [];
  private subscription: Subscription[] = [];

  public id: number;
  public isSeance: boolean;
  private receivedData: any;
  private base64Data: any;
  public convertedImg: any;

  constructor(private route: ActivatedRoute,
              private movieService: MovieService,
              private seanceService: SeanceService,
              private matDialog: MatDialog
  ) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getMovieModelById();
    this.getSeanceModelsByMovie();
  }

  private getMovieModelById(): void {
    this.subscription.push(this.movieService.getMovieModelById(this.id)
      .subscribe(arg => {
        this.movieModel = arg;
        this.receivedData = this.movieModel.poster;
        this.base64Data = this.receivedData.pic;
        this.convertedImg = /*'data:image/jpeg;base64,' + */this.base64Data;
      })
    );
  }

  private getSeanceModelsByMovie(): void {
    this.subscription.push(this.seanceService.getSeanceModelsByMovie(this.id)
      .subscribe(
        arg => {
          this.seances = arg;
        },
        err => { },
        () => this.getAllCinemasByMovie()
      )
    );
  }

  private getAllCinemasByMovie(): void {
    this.seances.forEach(seance => {
      if (this.cinemas.some(c => c.idCinema !== seance.hall.cinema.idCinema) ||
        this.cinemas.length === 0) {
        this.cinemas.push(seance.hall.cinema);
      }
    });
  }

  private getIdFromPath(): void {
    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data => this.id = +data);
  }

  public openPlaceDialog(seanceModel: SeanceModel): void {
    this.matDialog.open(PalceComponent, {
      width: '500px',
      height: '500px',
      data: {seance: seanceModel},
    });
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}


