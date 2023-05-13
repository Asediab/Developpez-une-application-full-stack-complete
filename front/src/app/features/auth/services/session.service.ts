import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
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
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

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
    return token ? token : '';
  }

  public setToken(value: string) {
    localStorage.setItem(this.tokenKey, value);
  }

  public clearData() {
    localStorage.clear();
  }


  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
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
    this.authService.me().subscribe((user: UserInterface) => this.authUser = user);
    if (this.authUser != undefined) {
      this.next();
    } else {
      this.logOut()
    }
  }

  public logOut(): void {
    this.clearData()
    this.authUser = undefined;
    this.isLogged = false;
    this.next();
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
