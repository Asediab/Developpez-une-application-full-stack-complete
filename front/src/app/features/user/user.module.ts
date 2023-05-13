import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserDetailComponent} from './components/user-detail/user-detail.component';
import {UserRoutingModule} from "./user-routing.module";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {SubjectsModule} from "../subjects/subjects.module";


@NgModule({
  declarations: [
    UserDetailComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatProgressSpinnerModule,
    SubjectsModule
  ]
})
export class UserModule {
}
