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
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";


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
    RouterLink,
    RouterLinkActive,
    A11yModule,
    MatDialogModule,
    MatToolbarModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatInputModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    FlexModule,
    FormsModule,
  ]
})
export class SharedModulesModule {
}
