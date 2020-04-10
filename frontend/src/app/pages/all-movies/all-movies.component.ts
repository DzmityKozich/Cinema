import { MovieService } from './../../services/movie.service';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MovieModel } from 'src/app/classes/movie-model';

@Component({
  selector: 'app-all-movies',
  templateUrl: './all-movies.component.html',
  styleUrls: ['./all-movies.component.css']
})
export class AllMoviesComponent implements OnInit, OnDestroy {

  public movies: MovieModel[] = [];
  private subscription: Subscription[] = [];
  public pageNumber = 0;

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.getAllMoviesModelByPage();
  }

  private getAllMoviesModelByPage(): void {
    this.subscription.push(this.movieService.getAllMovieModelsByPage(this.pageNumber, 9)
      .subscribe(arg => this.movies = arg)
    );
  }

  public nextPage(): void {
    this.pageNumber += 1;
    this.getAllMoviesModelByPage();
  }

  public previousPage(): void {
    this.pageNumber -= 1;
    this.getAllMoviesModelByPage();
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
