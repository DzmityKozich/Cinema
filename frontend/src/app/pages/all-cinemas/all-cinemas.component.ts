import { Subscription } from 'rxjs';
import { PaginationPage } from './../../classes/pagination-page';
import { CinemaModel } from './../../classes/cinema-model';
import { CinemaService } from './../../services/cinema.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import {PageEvent} from '@angular/material/paginator';

@Component({
  selector: 'app-all-cinemas',
  templateUrl: './all-cinemas.component.html',
  styleUrls: ['./all-cinemas.component.css']
})
export class AllCinemasComponent implements OnInit, OnDestroy {

  public paginationPage: PaginationPage<CinemaModel> = new PaginationPage<CinemaModel>();
  private subscription: Subscription[] = [];

  public pageSizeOptions: number[] = [2, 4, 8];

  constructor(private cinemaService: CinemaService) { }

  ngOnInit() {
    this.getAllCinemaModelsByPage(0, 2);
  }

  changePage(event: PageEvent): void {
    this.getAllCinemaModelsByPage(event.pageIndex, event.pageSize);
  }

  private getAllCinemaModelsByPage(pageNumber, pageSize): void {
    this.subscription.push(this.cinemaService.getAllCinemasByPage(pageNumber, pageSize)
      .subscribe(arg => this.paginationPage = arg)
    );
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }
}
