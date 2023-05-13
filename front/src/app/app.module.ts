import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {SharedModulesModule} from "./shared-modules/shared-modules.module";
import {AuthModule} from "./features/auth/auth.module";
import {JwtInterceptor} from "./interceptors/jwt.interceptor";


const materialModule = [
  MatButtonModule,
  MatCardModule,
  MatIconModule,
  MatSnackBarModule,
  MatToolbarModule,
  MatInputModule,
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ...materialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    FlexModule,
    SharedModulesModule,
    AuthModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true}
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}
