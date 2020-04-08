import { MovieModel } from '../classes/movie-model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private path = '/api/movies/';

  constructor(private http: HttpClient) { }

  public getAllMovieModels(): Observable<MovieModel[]> {
    return this.http.get<MovieModel[]>(this.path);
  }

  public getMovieModelById(id: number): Observable<MovieModel> {
    return this.http.get<MovieModel>(this.path + id);
  }
}
