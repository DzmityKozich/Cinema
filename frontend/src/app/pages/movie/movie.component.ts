import { RoleService } from './../../services/role.service';
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
  public isAuthorized: boolean;

  public id: number;
  public isSeance: boolean;
  private date: Date = new Date();

  constructor(private route: ActivatedRoute,
              private movieService: MovieService,
              private seanceService: SeanceService,
              private matDialog: MatDialog,
              private roleService: RoleService
  ) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getMovieModelById();
    this.getAllSeanceModelsByDateAndMovie(format(this.date, 'YYYY-MM-DD'), this.id);
    this.isAuthorized = this.roleService.isAuthorized();
  }

  private getMovieModelById(): void {
    this.subscription.push(this.movieService.getMovieModelById(this.id)
      .subscribe(arg => {
        this.movieModel = arg;
      })
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

  public pickDate(event): void {
    this.clearCinemas();
    this.getAllSeanceModelsByDateAndMovie(event, this.id);
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
      .subscribe(dataPath => this.id = +dataPath);
  }

  public openPlaceDialog(seanceModel: SeanceModel): void {
    this.matDialog.open(PalceComponent, {
      minWidth: '500px',
      minHeight: '500px',
      maxWidth: '',
      maxHeight: '',
      data: {seance: seanceModel},
      disableClose: true
    });
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}


