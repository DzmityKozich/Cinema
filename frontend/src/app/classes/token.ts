import { UserModel } from './user-model';

export class Token {
  currentUser?: UserModel;
  token?: string;
}
