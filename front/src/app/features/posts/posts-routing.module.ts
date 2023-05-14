import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PostsListComponent} from "./components/posts-list/posts-list.component";
import {PostDetailComponent} from "./components/post-detail/post-detail.component";
import {PostCreateComponent} from "./components/post-create/post-create.component";

const routes: Routes = [
  {title: 'Posts', path: '', component: PostsListComponent},
  {title: 'Post detail', path: ':id', component: PostDetailComponent},
  {title: 'Create Post', path: 'create', component: PostCreateComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule {
}
