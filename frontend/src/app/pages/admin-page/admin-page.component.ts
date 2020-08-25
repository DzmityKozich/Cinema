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
import { FormControl, FormGroup, Validators, FormArray } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HallModel } from 'src/app/classes/hall-model';
import { MatExpansionPanel } from '@angular/material/expansion';
import { Mail } from 'src/app/classes/mail';
import { CinemaService } from 'src/app/services/cinema.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit, OnDestroy {

  @ViewChild('panelMovie', { static: false })
  private panelMovie: MatExpansionPanel;

  @ViewChild('panelSeance', { static: false })
  private panelSeance: MatExpansionPanel;

  @ViewChild('panelMail', { static: false })
  private panelMail: MatExpansionPanel;

  @ViewChild('panelCinema', { static: false })
  private panelCinema: MatExpansionPanel;

  public movieModel: MovieModel = new MovieModel();
  public movies: MovieModel[] = [];
  public seanceModel: SeanceModel = new SeanceModel();
  public halls: HallModel[] = [];
  public hallsModels: HallModel[] = [];
  public logins: LoginModel[] = [];
  private mail: Mail = new Mail();
  private subscription: Subscription[] = [];
  private currentUser: UserModel = new UserModel();
  private cinemaModel = new CinemaModel();

  // tslint:disable-next-line: no-inferrable-types
  public isAll: boolean = false;
  private dataUrl: any;
  private isImg: boolean;
  d: Date = new Date();

  public genres: string[] = [
    'Action',
    'Comedy',
    'Crime',
    'Fantasy',
    'Adventure',
    'Thriller',
    'Detective',
    'Western',
    'Historical'
  ];

  // tslint:disable-next-line: variable-name
  public _places: number[] = [3, 4, 5, 6, 7];

  constructor(private movieService: MovieService,
              private seanceService: SeanceService,
              private hallService: HallService,
              private cinemaService: CinemaService,
              private snackBar: MatSnackBar,
              private mailService: MailService,
              private loginService: LoginService,
              private signInService: SignInService
  ) { }

  formMovie: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,45}')]),
    genre: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,999}')]),
    poster: new FormControl('', [Validators.required])
  });

  formSeance: FormGroup = new FormGroup({
    date: new FormControl('', [Validators.required]),
    time: new FormControl(new Date(), [Validators.required]),
    price: new FormControl('', [Validators.required, Validators.pattern('[0-9]{1,10}(\.[0-9]{1,2})?')]),
    movie: new FormControl('', [Validators.required]),
    hall: new FormControl('', [Validators.required])
  });

  formMail: FormGroup = new FormGroup({
    to: new FormControl('', [Validators.required]),
    subject: new FormControl('', [Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,45}')]),
    text: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,999}')])
  });

  formCinema: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,45}')]),
    address: new FormControl('', [Validators.required]),
    img: new FormControl('', [Validators.required])
  });

  formHalls = new FormArray([
    new FormGroup({
      name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,45}')]),
      rows: new FormControl('', [Validators.required]),
      placesInRow: new FormControl('', [Validators.required])
    })
  ]);

  ngOnInit() {
    this.currentUser = this.signInService.getCurrentUser();
    this.getAllMovieModels();
    this.getAllHallModels();
    this.getAllLoginModels();
  }

  public addMovie(): void {
    if (this.formMovie.valid && this.isImg) {
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

  private sendMail(mail: Mail): void {
    this.subscription.push(this.mailService.sendMail(mail)
      .subscribe(() => {
        this.refreshMail();
      },
        (err) => { },
        () => {
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
          this.clearForm(this.formMail);
          this.panelMail.close();
        }
      )
    );
  }

  public addCinema() {
    if (this.formCinema.valid && this.isImg && this.formHalls.valid) {
      this.cinemaModel = this.formCinema.value;
      this.cinemaModel.img = this.dataUrl;
      this.saveCinemaModel();
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  public addHall(cinemaModel: CinemaModel) {
    if (this.formHalls.valid) {
      this.hallsModels = this.formHalls.value;
      this.hallsModels.forEach(arg => arg.cinema = cinemaModel);
      this.saveHallModels(this.hallsModels);
    } else {
      this.openSnackBar('Check your data', 'Ok', 2500);
    }
  }

  public setDate(event): void {
    this.formSeance.controls.date.setValue(event);
  }

  public saveMovieModel(): void {
    this.subscription.push(this.movieService.saveMovieModel(this.movieModel).subscribe(
      () => {
        this.refrechMovieModel();
        this.getAllMovieModels();
      },
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

  private saveCinemaModel(): void {
    this.subscription.push(this.cinemaService.saveCinemaModel(this.cinemaModel)
      .subscribe(arg => {
        this.addHall(arg);
        this.refreshCinemaModel();
      })
    );
  }

  private refreshCinemaModel(): void {
    this.cinemaModel = new CinemaModel();
  }

  private saveHallModels(halls: HallModel[]): void {
    this.subscription.push(this.hallService.saveHallModels(halls)
      .subscribe(() => {
        this.refreshHallModels();
      },
      (err) => { },
      () => {
        this.openSnackBar('Cinema add', 'Ok', 1500);
        this.clearForm(this.formCinema);
        this.clearForm(this.formHalls);
        this.clearFormHall();
        this.panelCinema.close();
      })
    );
  }

  private refreshHallModels(): void {
    this.hallsModels = [];
  }

  public onFileChange(event) {
    const fileReader: FileReader = new FileReader();
    const file = event.target.files[0];
    if (file.type.match('image.*')) {
      this.isImg = true;
      fileReader.readAsDataURL(file);
      fileReader.onload = () => {
        this.dataUrl = fileReader.result;
      };
    } else {
      this.isImg = false;
    }
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

  private refreshMail(): void {
    this.mail = new Mail();
  }

  private clearForm(form: any): void {
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

  public createNewHall() {

    const group = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.pattern('[^ ][ A-Za-z0-9,\!\?\.]{1,45}')]),
      rows: new FormControl('', [Validators.required]),
      placesInRow: new FormControl('', [Validators.required])
    });

    if (this.formHalls.length < 10) {
      this.formHalls.push(group);
    }
  }

  public clearFormHall(): void {
    this.formHalls.clear();
    this.createNewHall();
  }

  ngOnDestroy() {
    this.subscription.forEach(sub => sub.unsubscribe());
  }

}
