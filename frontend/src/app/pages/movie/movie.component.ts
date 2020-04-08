import { MovieService } from './../../services/movie.service';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { MovieModel } from 'src/app/classes/movie-model';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit, OnDestroy {

  public id: number;
  public movieModel: MovieModel = new MovieModel();
  private subscription: Subscription[] = [];

  constructor(private route: ActivatedRoute,
              private movieService: MovieService,
  ) { }

  ngOnInit() {
    this.getIdFromPath();
    this.getMovieModelById();
  }

  private getIdFromPath(): void {
    this.route.paramMap.pipe(
      switchMap(params => params.getAll('id'))
    )
      .subscribe(data => this.id = +data);
  }

  public getMovieModelById(): void {
    this.subscription.push(this.movieService.getMovieModelById(this.id)
      .subscribe(arg => this.movieModel = arg)
    );
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}


