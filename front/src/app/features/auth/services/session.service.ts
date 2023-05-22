import {Injectable, OnDestroy} from '@angular/core';
import {UserInterface} from "../../user/interfaces/user.interface";
import {AuthService} from "./auth.service";
import {SessionInformation} from "../interfaces/sessionInformation.interface";
import {Subscription, take} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SessionService implements OnDestroy {
  private tokenKey: string = 'app-token';
  public authUser: UserInterface | undefined;
  private subs: Subscription;

  constructor(private authService: AuthService) {
  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
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
    this.getMe();
  }

  public updateUser(): void {
    this.getMe();
  }

  private getMe(): void {
    this.subs = this.authService.me()
      .pipe(take(1))
      .subscribe({
        next: (user: UserInterface) => {
          this.authUser = user;
        },
        error: err => this.logOut()
      });
  }

  public logOut(): void {
    this.clearData();
    this.authUser = undefined;
  }
}
