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


  // tslint:disable-next-line: no-inferrable-types
  private genre: string = 'All';
  public pageSizeOptions: number[] = [9, 6, 3];

  public genres: string[] = [
    'All',
    'Action',
    'Comedy',
    'Crime',
    'Fantasy',
    'Adventure',
    'Thriller',
    'Detective',
    'Western',
    'Historical'
  ];

  constructor(private movieService: MovieService) { }

  ngOnInit() {
    this.getAllMovieModelsByPage(0, 9);
  }

  changePage(event: PageEvent) {
    if (this.genre !== 'All') {
      this.getMovieModelsByGenre(this.genre, event.pageIndex, event.pageSize);
    } else {
      this.getAllMovieModelsByPage(event.pageIndex, event.pageSize);
    }
  }

  public chooseGenre(genre: string): void {
    if (genre !== 'All') {
      this.getMovieModelsByGenre(genre, 0, 9);
    } else {
      this.getAllMovieModelsByPage(0, 9);
    }
    this.genre = genre;
  }

  private getAllMovieModelsByPage(pageNumber: number, pageSize: number): void {
    this.subscription.push(this.movieService.getAllMovieModelsByPage(pageNumber, pageSize)
      .subscribe(arg => this.paginationPage = arg)
    );
  }

  private getMovieModelsByGenre(genre: string, pageNumber: number, pageSize: number): void {
    this.subscription.push(this.movieService.getAllMovieModelByGenre(genre, pageNumber, pageSize)
      .subscribe(arg => this.paginationPage = arg)
    );
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
