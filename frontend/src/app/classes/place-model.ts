import { SeanceModel } from './seance-model';

export class PlaceModel {
  idPlace: number;
  row: number;
  site: number;
  state: string;
  seance: SeanceModel;
  // billing: BillingModel
}
