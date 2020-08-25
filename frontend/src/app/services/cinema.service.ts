import { PaginationPage } from './../classes/pagination-page';
import { CinemaModel } from './../classes/cinema-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  private path = '/api/cinemas';

  constructor(private http: HttpClient) { }

  public getAllCinemasByPage(pageNumber: number, pageSize: number): Observable<PaginationPage<CinemaModel>> {
    return this.http.get<PaginationPage<CinemaModel>>(this.path + '?pageNumber=' + pageNumber + '&pageSize=' + pageSize);
  }

  public getCinemaModelById(id: number): Observable<CinemaModel> {
    return this.http.get<CinemaModel>(this.path + '/' + id);
  }

  public saveCinemaModel(cinema: CinemaModel): Observable<CinemaModel> {
    return this.http.post<CinemaModel>(this.path, cinema);
  }

}
