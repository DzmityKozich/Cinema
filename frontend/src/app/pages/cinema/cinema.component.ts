import { MatDialog } from '@angular/material/dialog';
import { MovieModel } from './../../classes/movie-model';
import { SeanceService } from './../../services/seance.service';
import { SeanceModel } from './../../classes/seance-model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { CinemaService } from './../../services/cinema.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CinemaModel } from 'src/app/classes/cinema-model';
import { switchMap } from 'rxjs/operators';
import { PalceComponent } from 'src/app/modules/palce/palce.component';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit, OnDestroy {

  public cinema: CinemaModel = new CinemaModel();
  public seances: SeanceModel[] = [];
  public movies: MovieModel[] = [];
  private subscription: Subscription[] = [];

  private id: number;
  private base64Data: any;
  public convertedImg: any;
  private receivedData: any;

  constructor(private cinemaService: CinemaService,
              private route: ActivatedRoute,
              private seanceService: SeanceService,
              private matDialog: MatDialog
) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getCinemaModelById();
    this.getSeanceModelsByCinemasAndDate('2020-05-14');
  }

  private getCinemaModelById(): void {
    this.subscription.push(this.cinemaService.getCinemaModelById(this.id)
      .subscribe(arg => {
          this.cinema = arg;
          this.receivedData = this.cinema.img;
          this.base64Data = this.receivedData.pic;
          this.convertedImg = this.base64Data;
        }
      )
    );
  }

  private getIdFromPath(): void {
    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data => this.id = +data);
  }

  private getSeanceModelsByCinemasAndDate(date: string): void {
    this.subscription.push(this.seanceService.getAllSeanceModelsByCinemaAndDate(this.id, date)
      .subscribe(arg => {
        this.seances = arg;
        this.getAllMovieModelsByCinemas();
      }
        )
    );
  }

  private getAllMovieModelsByCinemas(): void {
    this.seances.forEach(seance => {
      const isNotExist: boolean = this.movies.every(movie => movie.idMovie !== seance.movie.idMovie);
      const isEmpty: boolean = this.movies.length === 0;
      if (isNotExist || isEmpty) {
        this.movies.push(seance.movie);
      }
    });
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

  public pickDate(event): void {
    this.clearMovies();
    this.getSeanceModelsByCinemasAndDate(event);
  }

  private clearMovies(): void {
    this.movies = [];
  }

  ngOnDestroy() {

  }

}
