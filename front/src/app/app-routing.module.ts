import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NotFoundComponent} from "./components/not-found/not-found.component";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./features/auth/auth.module').then(m => m.AuthModule)
  },
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
