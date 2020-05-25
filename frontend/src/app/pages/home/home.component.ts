import { SignInService } from './../../services/sign-in.service';
import { PaginationPage } from './../../classes/pagination-page';
import { CinemaService } from './../../services/cinema.service';
import { MovieService } from './../../services/movie.service';
import { MovieModel } from '../../classes/movie-model';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { CinemaModel } from 'src/app/classes/cinema-model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  public moviePaginationPage: PaginationPage<MovieModel> = new PaginationPage<MovieModel>();
  public cinemaPaginationPage: PaginationPage<CinemaModel> = new PaginationPage<CinemaModel>();
  public movies: MovieModel[] = [];
  public movie: MovieModel = new MovieModel();
  public cinemas: CinemaModel[] = [];
  private subscriptions: Subscription[] = [];

  constructor(private movieService: MovieService,
              private cinemaService: CinemaService,
  ) { }

  ngOnInit() {
    this.getMovieModels();
    this.getCinemaModels();
  }

  private getMovieModels(): void {
    this.subscriptions.push(this.movieService.getAllMovieModelsByPage(0, 3)
      .subscribe(arg => this.moviePaginationPage = arg)
    );
  }

  private getCinemaModels(): void {
    this.subscriptions.push(this.cinemaService.getAllCinemasByPage(0, 3)
      .subscribe(arg => this.cinemaPaginationPage = arg)
    );
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

}
