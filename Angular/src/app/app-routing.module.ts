import { DogDetailsComponent } from './dog-details/dog-details.component';
import { CreateDogComponent } from './create-dog/create-dog.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DogListComponent } from './dog-list/dog-list.component';
import { UpdateDogComponent } from './update-dog/update-dog.component';

const routes: Routes = [
  { path: '', redirectTo: 'dog', pathMatch: 'full' },
  { path: 'dogs', component: DogListComponent },
  { path: 'add', component: CreateDogComponent },
  { path: 'update/:id', component: UpdateDogComponent },
  { path: 'details/:id', component: DogDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
