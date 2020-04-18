import { HallModel } from './hall-model';
import { MovieModel } from 'src/app/classes/movie-model';

export class SeanceModel {
  idSeance: number;
  date: string;
  time: string;
  price: number;
  move: MovieModel;
  hall: HallModel;
}
