import { PaginationPage } from './../../classes/pagination-page';
import { MovieService } from './../../services/movie.service';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MovieModel } from 'src/app/classes/movie-model';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-all-movies',
  templateUrl: './all-movies.component.html',
  styleUrls: ['./all-movies.component.css']
})
export class AllMoviesComponent implements OnInit, OnDestroy {

  public paginationPage: PaginationPage<MovieModel> = new PaginationPage<MovieModel>();
  public movies: MovieModel[] = [];
  private subscription: Subscription[] = [];
  pageSizeOptions: number[] = [9, 6, 3];

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.getAllMoviesModelByPage(0, 9);
  }

  changePage(event: PageEvent) {
    this.subscription.push(this.movieService.getAllMovieModelsByPage(event.pageIndex, event.pageSize)
      .subscribe(arg => this.paginationPage = arg)
    );
  }

  private getAllMoviesModelByPage(pageNumber: number, pageSize: number): void {
    this.subscription.push(this.movieService.getAllMovieModelsByPage(pageNumber, pageSize)
      .subscribe(arg => this.paginationPage = arg)
    );
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
