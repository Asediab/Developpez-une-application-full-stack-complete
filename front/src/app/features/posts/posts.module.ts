import {NgModule} from '@angular/core';
import {CommonModule, NgOptimizedImage} from '@angular/common';
import {PostCardComponent} from './components/post-card/post-card.component';
import {PostsListComponent} from './components/posts-list/posts-list.component';
import {PostDetailComponent} from './components/post-detail/post-detail.component';
import {CommentsComponent} from './components/comments/comments.component';
import {PostsRoutingModule} from "./posts-routing.module";
import {MatRippleModule} from "@angular/material/core";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatListModule} from "@angular/material/list";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {PostCreateComponent} from './components/post-create/post-create.component';
import {MatSelectModule} from "@angular/material/select";


@NgModule({
  declarations: [
    PostCardComponent,
    PostsListComponent,
    PostDetailComponent,
    CommentsComponent,
    PostCreateComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    MatRippleModule,
    MatCardModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatListModule,
    ReactiveFormsModule,
    MatInputModule,
    MatSelectModule,
    NgOptimizedImage
  ],
  exports: [
    PostCardComponent,
    PostsListComponent,
    PostDetailComponent,
    CommentsComponent,
    PostCreateComponent
  ]
})
export class PostsModule {
}
