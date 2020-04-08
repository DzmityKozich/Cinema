import { MovieService } from './../../services/movie.service';
import { MovieModel } from '../../classes/movie-model';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public movies: MovieModel[] = [];
  public movie: MovieModel = new MovieModel();
  private subscriptions: Subscription[] = [];

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.getAllMovieModels();
  }

  private getAllMovieModels(): void {
    this.subscriptions.push(this.movieService.getAllMovieModels(0, 3)
      .subscribe(arg => this.movies = arg)
    );
  }

}
