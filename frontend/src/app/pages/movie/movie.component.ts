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
import { BsLocaleService, BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { format } from 'ts-date/esm/locale/en';

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

  bsConfig: Partial<BsDatepickerConfig>;
  public id: number;
  public isSeance: boolean;
  private receivedData: any;
  private base64Data: any;
  public convertedImg: any;

  constructor(private route: ActivatedRoute,
              private movieService: MovieService,
              private seanceService: SeanceService,
              private matDialog: MatDialog,
              private localeService: BsLocaleService
  ) { }

  ngOnInit() {
    this.datepickerConfig();
    this.getIdFromPath();
    this.getMovieModelById();
    this.getAllSeanceModelsByDateAndMovie('2020-05-14', this.id);
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
        () => { },
        () => this.getAllCinemasByMovie()
      )
    );
  }

  private getAllSeanceModelsByDateAndMovie(date: string, movieId: number): void {
    this.subscription.push(this.seanceService.getAllSeanceModelsByDateAndMovie(date, movieId)
      .subscribe(
        arg => {
          this.seances = arg;
          this.getAllCinemasByMovie();
        },
        (err) => { },
        () => { }
        )
    );
  }

  public pickDate(event: Date): void {
    const d = format(event, 'YYYY-MM-DD');
    this.clearCinemas();
    this.getAllSeanceModelsByDateAndMovie(d, this.id);
  }

  private getAllCinemasByMovie(): void {
    this.seances.forEach(seance => {
      if (this.cinemas.every(cinema => cinema.idCinema !== seance.hall.cinema.idCinema) ||
        this.cinemas.length === 0) {
        this.cinemas.push(seance.hall.cinema);
      }
    });
  }

  private clearCinemas(): void {
    this.cinemas = [];
  }

  private getIdFromPath(): void {
    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data => this.id = +data);
  }

  public datepickerConfig(): void {
    this.localeService.use('engb');
    this.bsConfig = Object.assign({},
      { containerClass: 'theme-default',
      showWeekNumbers: false,
      isAnimated: true,
      dateInputFormat: 'YYYY-MM-DD'
     });
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


