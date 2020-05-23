import { Observable } from 'rxjs';
import { Mail } from './../classes/mail';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private readonly path: string = '/api/mail';

  constructor(private http: HttpClient) { }

  public sendMail(mail: Mail): Observable<Mail> {
    return this.http.post<Mail>(this.path, mail);
  }

  public sendMailForAllUsers(mail: Mail): Observable<Mail> {
    return this.http.post<Mail>(this.path + '/all', mail);
  }
}
