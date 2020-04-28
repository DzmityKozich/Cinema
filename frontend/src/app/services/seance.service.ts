import { SeanceModel } from './../classes/seance-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CinemaModel } from '../classes/cinema-model';

@Injectable({
  providedIn: 'root'
})
export class SeanceService {

  private path = '/api/seances';

  constructor(private http: HttpClient) { }

  public getSeanceModelsByMovie(id: number): Observable<SeanceModel[]> {
    return this.http.get<SeanceModel[]>(this.path + '/movies/' + id);
  }

  public getAllSeanceModelsByDateAndMovie(date: string, movieId: number): Observable<SeanceModel[]> {
    return this.http.get<SeanceModel[]>(this.path + '/date/' + date + '/movies/' + movieId);
  }
}
