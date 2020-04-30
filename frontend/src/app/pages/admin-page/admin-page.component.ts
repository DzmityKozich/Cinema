import { SeanceService } from './../../services/seance.service';
import { SeanceModel } from './../../classes/seance-model';
import { HallService } from './../../services/hall.service';
import { Subscription } from 'rxjs';
import { MovieService } from './../../services/movie.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MovieModel } from 'src/app/classes/movie-model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HallModel } from 'src/app/classes/hall-model';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit, OnDestroy {

  public movieModel: MovieModel = new MovieModel();
  public movies: MovieModel[] = [];
  public seanceModel: SeanceModel = new SeanceModel();
  public halls: HallModel[] = [];
  private subscription: Subscription[] = [];

  private dataUrl: any;
  d: Date = new Date();

  public genres: string[] = [
    'Action'
  ];

  constructor(private movieService: MovieService,
              private seanceService: SeanceService,
              private hallService: HallService,
              private snackBar: MatSnackBar
  ) { }

  formMovie: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9]{1,45}')]), // [A-Za-z0-9\s]{1,45}
    genre: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9]{1,999}')]),
    poster: new FormControl('', [Validators.required])
  });

  formSeance: FormGroup = new FormGroup({
    // нет прошедшему времени!!!
    date: new FormControl('', [Validators.required]),
    time: new FormControl(new Date(), [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern('[0-9]{1,10}(\.\d{1,2})?')]),
    movie: new FormControl('', [Validators.required]),
    hall: new FormControl('', [Validators.required])
  });

  ngOnInit() {
    this.getAllMovieModels();
    this.getAllHallModels();
  }

  public addMovie(): void {
    if (this.formMovie.valid) {
      this.movieModel = this.formMovie.value;
      this.movieModel.poster = this.dataUrl;
      this.saveMovieModel();
      this.clearForm();
    }
  }

  public addSeance(): void {
    if (this.formSeance.valid) {
      this.formSeance.controls.time.setValue(this.formSeance.get('time').value.toLocaleTimeString());
      this.seanceModel = this.formSeance.value;
      console.log(this.seanceModel);
      this.saveSeanceModel();
    }
  }

  public setDate(event): void {
    this.formSeance.controls.date.setValue(event);
  }

  public saveMovieModel(): void {
    this.subscription.push(this.movieService.saveMovieModel(this.movieModel).subscribe(
      () => this.refrechMovieModel(),
      err => console.log(err),
      () => this.openSnackBar('Movie add', 'Ok', 1500)
    ));
  }

  private refrechMovieModel(): void {
    this.movieModel = new MovieModel();
  }

  private saveSeanceModel(): void {
    this.subscription.push(this.seanceService.saveSeanceModel(this.seanceModel)
      .subscribe(
        () => this.refreshSeanceModel(),
        (err) => { },
        () => this.openSnackBar('Seance add', 'Ok', 1500)
        )
    );
  }

  private refreshSeanceModel(): void {
    this.seanceModel = new SeanceModel();
  }

  public onFileChange(event) {
    const fileReader: FileReader = new FileReader();
    fileReader.readAsDataURL(event.target.files[0]);
    fileReader.onload = () => {
      this.dataUrl = fileReader.result;
    };
  }

  private clearForm(): void {
    this.formMovie.controls.name.setValue(null);
    this.formMovie.controls.genre.setValue(null);
    this.formMovie.controls.description.setValue(null);
    this.formMovie.controls.poster.setValue(null);
  }

  private getAllMovieModels(): void {
    this.subscription.push(this.movieService.getAllMovieModels()
      .subscribe(arg => this.movies = arg)
    );
  }

  private getAllHallModels(): void {
    this.subscription.push(this.hallService.getAllHallModels()
      .subscribe(arg => this.halls = arg)
    );
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, { duration: time });
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
