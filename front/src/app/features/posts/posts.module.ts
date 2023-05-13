import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PostCardComponent} from './components/post-card/post-card.component';
import {PostsListComponent} from './components/posts-list/posts-list.component';
import {PostDetailComponent} from './components/post-detail/post-detail.component';
import {CommentsComponent} from './components/comments/comments.component';
import {PostsRoutingModule} from "./posts-routing.module";


@NgModule({
  declarations: [
    PostCardComponent,
    PostsListComponent,
    PostDetailComponent,
    CommentsComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule
  ],
  exports: [
    PostCardComponent,
    PostsListComponent,
    PostDetailComponent,
    CommentsComponent
  ]
})
export class PostsModule {
}
