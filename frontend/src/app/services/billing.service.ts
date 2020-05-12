import { BillingModel } from './../classes/billing-model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BillingService {

  private readonly path = '/api/billings';

  constructor(private http: HttpClient) { }

  public getBillingModelById(): Observable<BillingModel> {
    return this.http.get<BillingModel>(this.path + '/' + 1);
  }

  public getBillingModelByUser(id: number): Observable<BillingModel> {
    return this.http.get<BillingModel>(this.path + '/users/' + id);
  }

  public putMoney(userId: number, money: number): Observable<BillingModel> {
    return this.http.post<BillingModel>(this.path + '/money/' + userId, money);
  }
}
