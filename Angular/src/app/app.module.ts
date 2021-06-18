import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateDogComponent } from './create-dog/create-dog.component';
import { DogDetailsComponent } from './dog-details/dog-details.component';
import { DogListComponent } from './dog-list/dog-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateDogComponent } from './update-dog/update-dog.component';
@NgModule({
  declarations: [
    AppComponent,
    CreateDogComponent,
    DogDetailsComponent,
    DogListComponent,
    UpdateDogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
