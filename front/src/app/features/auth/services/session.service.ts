import {Injectable} from '@angular/core';
import {UserInterface} from "../../user/interfaces/user.interface";
import {AuthService} from "./auth.service";
import {SessionInformation} from "../interfaces/sessionInformation.interface";

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private tokenKey: string = 'app-token';
  public isLogged = false;
  public authUser: UserInterface | undefined;

  constructor(private authService: AuthService) {
  }

  public setData(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  public removeData(key: string) {
    localStorage.removeItem(key);
  }

  public getToken() {
    let token = localStorage.getItem(this.tokenKey);
    return token ? token : undefined;
  }

  public setToken(value: string) {
    localStorage.setItem(this.tokenKey, value);
  }

  public clearData() {
    localStorage.clear();
  }

  public logIn(sessionInformation: SessionInformation): void {
    this.setData(this.tokenKey, sessionInformation.token)
    this.isLogged = true;
    this.getMe();
  }

  public updateUser(): void {
    this.getMe();
  }

  private getMe(): void {
    this.authService.me().subscribe({
      next: (user: UserInterface) => {
        this.authUser = user;
        console.log(user);
      }
    });
    if (this.authUser == undefined) {
      //this.logOut();
    }
  }

  public logOut(): void {
    this.clearData();
    this.authUser = undefined;
    this.isLogged = false;
  }
}
