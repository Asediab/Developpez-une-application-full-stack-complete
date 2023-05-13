import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./shared-modules/not-found/not-found.component";
import {LandingComponent} from "./shared-modules/landing/landing.component";
import {RegisterComponent} from "./features/auth/components/register/register.component";
import {LoginComponent} from "./features/auth/components/login/login.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [

  {
    path: '',
    component: LandingComponent
  },
  {
    path: 'posts',
    loadChildren: () =>
      import('./features/posts/posts.module').then((m) => m.PostsModule),
  },
  {
    path: 'subjects',
    loadChildren: () =>
      import('./features/subjects/subjects.module').then((m) => m.SubjectsModule),
  },
  {
    path: 'me',
    loadChildren: () =>
      import('./features/user/user.module').then((m) => m.UserModule),
  },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
