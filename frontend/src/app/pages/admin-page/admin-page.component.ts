import { Subscription } from 'rxjs';
import { MovieService } from './../../services/movie.service';
import { Component, OnInit } from '@angular/core';
import { MovieModel } from 'src/app/classes/movie-model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  public movieModel: MovieModel = new MovieModel();
  private subscription: Subscription[] = [];

  private dataUrl: any;

  public genres: string[] = [
    'Action'
  ];

  constructor(private movieService: MovieService,
              private snackBar: MatSnackBar) { }

  form: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    genre: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    poster: new FormControl('', [Validators.required])
  });

  ngOnInit() {
  }

  private refrech(): void {
    this.movieModel = new MovieModel();
  }

  public saveMovieModel(): void {
    this.subscription.push(this.movieService.saveMovieModel(this.movieModel).subscribe(
      () => this.refrech(),
      err => console.log(err),
      () => this.openSnackBar('Movie add', 'Ok', 1500)
    ));
  }

  public addMovie() {
    if (this.form.valid) {
      this.movieModel = this.form.value;
      this.movieModel.poster = this.dataUrl;
      this.saveMovieModel();
      this.clearForm();
    }
  }

  public onFileChange(event) {
    const fileReader: FileReader = new FileReader();
    fileReader.readAsDataURL(event.target.files[0]);
    fileReader.onload = () => {
      this.dataUrl = fileReader.result;
    };
  }

  private clearForm(): void {
    this.form.controls.name.setValue(null);
    this.form.controls.genre.setValue(null);
    this.form.controls.description.setValue(null);
    this.form.controls.poster.setValue(null);
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, {duration: time});
  }

}
