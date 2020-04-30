import { PaginationPage } from './../classes/pagination-page';
import { MovieModel } from '../classes/movie-model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private path = '/api/movies';

  constructor(private http: HttpClient) { }

  public getAllMovieModels(): Observable<MovieModel[]> {
    return this.http.get<MovieModel[]>(this.path);
  }

  public getAllMovieModelsByPage(pageNumber: number, pageSize: number): Observable<PaginationPage<MovieModel>> {
    return this.http.get<PaginationPage<MovieModel>>(this.path + '?pageNumber=' + pageNumber + '&pageSize=' + pageSize);
  }

  public getMovieModelById(id: number): Observable<MovieModel> {
    return this.http.get<MovieModel>(this.path + '/' + id);
  }

  public saveMovieModel(movie: MovieModel): Observable<MovieModel> {
    return this.http.post<MovieModel>(this.path, movie);
  }
}
