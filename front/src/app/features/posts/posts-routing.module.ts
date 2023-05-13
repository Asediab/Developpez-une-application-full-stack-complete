import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PostsListComponent} from "./components/posts-list/posts-list.component";
import {PostDetailComponent} from "./components/post-detail/post-detail.component";

const routes: Routes = [
  // TODO path create
  {title: 'Posts', path: '', component: PostsListComponent},
  {title: 'Post detail', path: ':id', component: PostDetailComponent},
  {title: 'Create Post', path: 'create', component: PostDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule {
}
