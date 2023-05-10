import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './pages/home/home.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";
import {HttpClientModule} from "@angular/common/http";


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
    AppComponent,
    HomeComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ...materialModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlexLayoutModule,
    FlexModule
  ],
  //TODO providers
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
