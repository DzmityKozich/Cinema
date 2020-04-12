import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { CinemaService } from './../../services/cinema.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CinemaModel } from 'src/app/classes/cinema-model';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit, OnDestroy {

  public cinema: CinemaModel = new CinemaModel();
  private subscription: Subscription[] = [];

  private id: number;

  constructor(private cinemaService: CinemaService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getCinemaModelById();
  }

  private getCinemaModelById(): void {
    this.subscription.push(this.cinemaService.getCinemaModelById(this.id)
      .subscribe(arg => this.cinema = arg)
    );
  }

  private getIdFromPath(): void {
    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data => this.id = +data);
  }

  ngOnDestroy() {

  }

}
