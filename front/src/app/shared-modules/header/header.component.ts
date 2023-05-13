import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {BreakpointObserver, Breakpoints, BreakpointState} from "@angular/cdk/layout";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {SessionService} from "../../features/auth/services/session.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  isDesktop!: boolean;

  constructor(
    private router: Router,
    private breakpointObserver: BreakpointObserver,
    public dialog: MatDialog,
    private sessionService: SessionService
  ) {
  }

  openMenu(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.height = '100%';
    dialogConfig.width = '184px';
    dialogConfig.position = {right: '0px', top: '0px'};
    dialogConfig.ariaLabel = 'Menu';
    dialogConfig.autoFocus = false;
    dialogConfig.panelClass = 'app-dialog-container';
    this.dialog.open(HeaderMenuContent, dialogConfig);
  }

  ngOnInit() {
    this.breakpointObserver
      .observe([Breakpoints.XSmall])
      .subscribe((result: BreakpointState) => {
        this.isDesktop = !result.matches;
      });
  }

  shouldShowMenu(): boolean {
    const currentUrl = this.router.url;
    return !(currentUrl === '/login' ||
      currentUrl === '/register' ||
      (currentUrl === '/404' && !this.sessionService.isLogged));

  }
}

@Component({
  selector: 'header-menu-content',
  templateUrl: 'header-menu-content.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderMenuContent {
  constructor(public dialog: MatDialog, private router: Router) {
  }

  closeMenu() {
    this.dialog.closeAll();
  }
}
