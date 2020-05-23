import { UserModel } from './../../classes/user-model';
import { SignInService } from './../../services/sign-in.service';
import { LoginService } from './../../services/login.service';
import { LoginModel } from './../../classes/login-model';
import { MailService } from './../../services/mail.service';
import { CinemaModel } from 'src/app/classes/cinema-model';
import { SeanceService } from './../../services/seance.service';
import { SeanceModel } from './../../classes/seance-model';
import { HallService } from './../../services/hall.service';
import { Subscription } from 'rxjs';
import { MovieService } from './../../services/movie.service';
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { MovieModel } from 'src/app/classes/movie-model';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HallModel } from 'src/app/classes/hall-model';
import { MatExpansionPanel } from '@angular/material/expansion';
import { Mail } from 'src/app/classes/mail';
import { MatSlideToggle } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit, OnDestroy {

  /**
   * сделать ошибки при незаполенных полях для картинки, даты, времени и тп
   */

  @ViewChild('panelMovie', { static: false })
  private panelMovie: MatExpansionPanel;

  @ViewChild('panelSeance', { static: false })
  private panelSeance: MatExpansionPanel;

  @ViewChild('panelCinema', { static: false })
  private panelCinema: MatExpansionPanel;

  @ViewChild('panelMail', { static: false })
  private panelMail: MatExpansionPanel;

  public movieModel: MovieModel = new MovieModel();
  public movies: MovieModel[] = [];
  public seanceModel: SeanceModel = new SeanceModel();
  public halls: HallModel[] = [];
  public logins: LoginModel[] = [];
  private cinema: CinemaModel = new CinemaModel();
  private mail: Mail = new Mail();
  private subscription: Subscription[] = [];
  private currentUser: UserModel = new UserModel();

  // tslint:disable-next-line: no-inferrable-types
  public isAll: boolean = false;
  private dataUrl: any;
  d: Date = new Date();

  public genres: string[] = [
    'Action',
    'Comedy',
    'Crime',
    'Fantasy',
    'Adventure',
    'Thriller',
    'Detective',
    'Western'
  ];

  constructor(private movieService: MovieService,
              private seanceService: SeanceService,
              private hallService: HallService,
              private snackBar: MatSnackBar,
              private mailService: MailService,
              private loginService: LoginService,
              private signInService: SignInService
  ) { }

  formMovie: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9]{1,45}')]),
    genre: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9]{1,999}')]),
    poster: new FormControl('', [Validators.required])
  });

  formSeance: FormGroup = new FormGroup({
    date: new FormControl('', [Validators.required]),
    time: new FormControl(new Date(), [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern('[0-9]{1,10}(\.[0-9]{1,2})?')]),
    movie: new FormControl('', [Validators.required]),
    hall: new FormControl('', [Validators.required])
  });

  formCinema: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9]{1,45}')]),
    address: new FormControl('', [Validators.required]), // ^\d+\s[A-Za-z]+\s[A-Za-z]+
    img: new FormControl('', [Validators.required])
  });

  formMail: FormGroup = new FormGroup({
    to: new FormControl('', [Validators.required]),
    subject: new FormControl('', [Validators.required]),
    text: new FormControl('', [Validators.required])
  });

  ngOnInit() {
    this.currentUser = this.signInService.getCurrentUser();
    this.getAllMovieModels();
    this.getAllHallModels();
    this.getAllLoginModels();
  }

  public addMovie(): void {
    if (this.formMovie.valid) {
      this.movieModel = this.formMovie.value;
      this.movieModel.poster = this.dataUrl;
      this.saveMovieModel();
      this.clearForm(this.formMovie);
      this.panelMovie.close();
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  public addSeance(): void {
    if (this.formSeance.valid) {
      this.formSeance.controls.time.setValue(this.formSeance.get('time').value.toLocaleTimeString());
      this.seanceModel = this.formSeance.value;
      this.saveSeanceModel();
      this.clearForm(this.formSeance);
      this.panelSeance.close();
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  public addCinema(): void {
    if (this.formCinema.valid) {
      this.cinema = this.formCinema.value;
      this.cinema.img = this.dataUrl;
      console.log(this.cinema);
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

  public createMail(): void {
    if (this.isAll) {
      this.sendMailForAllUsers(this.formMail.value);
    } else if (this.formMail.valid) {
      this.mail = this.formMail.value;
      this.mail.from = this.currentUser.firstName;
      this.sendMail(this.mail);
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  private sendMail(mail: Mail): void {
    this.subscription.push(this.mailService.sendMail(mail)
      .subscribe(() => {
        this.refreshMail();
      },
        (err) => { },
        () => {
          this.openSnackBar('Success', 'Ok', 1500);
          this.clearForm(this.formMail);
          this.panelMail.close();
        }
      )
    );
  }

  private sendMailForAllUsers(mail: Mail): void {
    this.subscription.push(this.mailService.sendMailForAllUsers(mail)
      .subscribe(() => {
        this.refreshMail();
      },
        (err) => { },
        () => {
          this.openSnackBar('Success', 'Ok', 1500);
          this.clearForm(this.formMail);
          this.panelMail.close();
        }
      )
    );
  }

  private refreshMail(): void {
    this.mail = new Mail();
  }

  private clearForm(form: FormGroup): void {
    form.reset();
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

  private getAllLoginModels(): void {
    this.subscription.push(this.loginService.getAllLoginModels()
      .subscribe(arg => this.logins = arg)
    );
  }

  private openSnackBar(message: string, action: string, time: number): void {
    this.snackBar.open(message, action, { duration: time });
  }

  public toggle(): void {
    this.isAll = !this.isAll;

  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
