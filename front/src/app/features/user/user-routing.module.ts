import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {UserDetailComponent} from "./components/user-detail/user-detail.component";

const routes: Routes = [
  {title: 'Me', path: '', component: UserDetailComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
