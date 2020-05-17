import { PlaceModel } from './../classes/place-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PlaceService {

  private readonly path = '/api/places';

  constructor(private http: HttpClient) { }

  public getAllPlaceModelsBySeance(id: number): Observable<PlaceModel[]> {
    return this.http.get<PlaceModel[]>(this.path + '/seances/' + id);
  }

  public getAllPlaceModelsByBilling(id: number): Observable<PlaceModel[]> {
    return this.http.get<PlaceModel[]>(this.path + '/billings/' + id);
  }

  public savePlaceModel(place: PlaceModel): Observable<PlaceModel> {
    return this.http.post<PlaceModel>(this.path, place);
  }

  public takePlace(places: PlaceModel[]): Observable<PlaceModel[]> {
    return this.http.post<PlaceModel[]>(this.path + '/reservation', places);
  }

  public clearSelectedPlaceModels(places: PlaceModel[]): Observable<PlaceModel[]> {
    return this.http.post<PlaceModel[]>(this.path + '/cleaning', places);
  }
}
