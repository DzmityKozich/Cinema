import { HallModel } from './../classes/hall-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HallService {

  private readonly path = '/api/halls';

  constructor(private http: HttpClient) { }

  public getAllHallModels(): Observable<HallModel[]> {
    return this.http.get<HallModel[]>(this.path);
  }

  public saveHallModels(hallModels: HallModel[]): Observable<HallModel[]> {
    return this.http.post<HallModel[]>(this.path, hallModels);
  }
}
