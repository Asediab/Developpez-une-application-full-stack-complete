import {Component, OnDestroy, OnInit} from '@angular/core';
import {AuthService} from "./features/auth/services/auth.service";
import {Router} from "@angular/router";
import {SessionService} from "./features/auth/services/session.service";
import {Subscription} from "rxjs";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit, OnDestroy {
  isDesktop!: boolean;
  private sub: Subscription;

  constructor(
    private authService: AuthService,
    private router: Router,
    private sessionService: SessionService,
    private breakpointObserver: BreakpointObserver) {
  }

  public autoLog(): void {
    if (this.sessionService.getToken()) {
      this.sessionService.updateUser()
      this.router.navigate(['/posts'])
    } else this.sessionService.logOut();
  }


  ngOnInit() {
    this.sub = this.breakpointObserver
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

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
