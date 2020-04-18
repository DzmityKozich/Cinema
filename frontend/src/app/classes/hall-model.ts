import { CinemaModel } from './cinema-model';

export class HallModel {
  idHall: number;
  name: string;
  rows: number;
  placesInRow: number;
  cinema: CinemaModel;
}
