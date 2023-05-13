import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SubjectCardComponent} from './components/subject-card/subject-card.component';
import {SubjectsListComponent} from './components/subjects-list/subjects-list.component';
import {SubjectsRoutingModule} from "./subjects-routing.module";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";


@NgModule({
  declarations: [
    SubjectCardComponent,
    SubjectsListComponent
  ],
  exports: [
    SubjectCardComponent,
    SubjectsListComponent
  ],
  imports: [
    CommonModule,
    SubjectsRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ]
})
export class SubjectsModule {
}
