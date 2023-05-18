import {Component, OnInit} from '@angular/core';
import {AuthService} from "./features/auth/services/auth.service";
import {Router} from "@angular/router";
import {SessionService} from "./features/auth/services/session.service";
import {take} from "rxjs";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {UserInterface} from "./features/user/interfaces/user.interface";
import {SessionInformation} from "./features/auth/interfaces/sessionInformation.interface";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  isDesktop!: boolean;
  private sessionInf: SessionInformation = {
    lastname: "", mail: "", token: "", type: ""
  };

  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService,
    private breakpointObserver: BreakpointObserver) {
  }

  public logout(): void {
    this.sessionService.logOut();
    this.router.navigate([''])
  }

  public autoLog(): void {
    if (this.sessionService.getToken()) {
      this.sessionInf.token = <string>this.sessionService.getToken();
      this.authService.me()
        .pipe(take(1))
        .subscribe({
          next: (user: UserInterface) => {
            this.sessionService.logIn(this.sessionInf);
          },
          error: (error) => {
            this.logout();
          },
        });
      this.router.navigate(['/posts'])
    } else this.logout()
  }


  ngOnInit() {
    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        this.isDesktop = !result.matches;
      });

    this.autoLog();
  }

  shouldShowHeader(): boolean {
    const currentUrl = this.router.url;
    return !(currentUrl === '/'
      ||
      (!this.isDesktop && currentUrl === '/login') ||
      (!this.isDesktop && currentUrl === '/register'));
  }
}
