import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {HeaderComponent, HeaderMenuContent} from './header/header.component';
import {LandingComponent} from './landing/landing.component';
import {NotFoundComponent} from "./not-found/not-found.component";
import {MatButtonModule} from "@angular/material/button";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {A11yModule} from "@angular/cdk/a11y";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatDialogModule} from "@angular/material/dialog";


@NgModule({
  declarations: [
    HeaderComponent,
    LandingComponent,
    NotFoundComponent,
    HeaderMenuContent
  ],
  exports: [
    HeaderComponent,
    HeaderMenuContent,
    LandingComponent,
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterLink,
    RouterLinkActive,
    A11yModule,
    MatDialogModule,
    MatToolbarModule
  ]
})
export class SharedModulesModule {
}
