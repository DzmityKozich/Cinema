import { MovieModel } from './../../classes/movie-model';
import { SeanceService } from './../../services/seance.service';
import { SeanceModel } from './../../classes/seance-model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { CinemaService } from './../../services/cinema.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CinemaModel } from 'src/app/classes/cinema-model';
import { switchMap } from 'rxjs/operators';

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

  constructor(private cinemaService: CinemaService,
              private route: ActivatedRoute,
              private seanceService: SeanceService
) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getCinemaModelById();
    this.getSeanceModelsByCinemasAndDate('2020-05-14');
  }

  private getCinemaModelById(): void {
    this.subscription.push(this.cinemaService.getCinemaModelById(this.id)
      .subscribe(arg => this.cinema = arg)
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
