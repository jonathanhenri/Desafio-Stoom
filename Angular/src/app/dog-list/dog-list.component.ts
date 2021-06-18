import { DogDetailsComponent } from '../dog-details/dog-details.component';
import { Observable } from "rxjs";
import { DogService } from "../dog.service";
import { Dog } from "../dog";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-dog-list",
  templateUrl: "./dog-list.component.html",
  styleUrls: ["./dog-list.component.css"]
})
export class DogListComponent implements OnInit {
  dogs: Observable<Dog[]>;

  constructor(private dogService: DogService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.dogs = this.dogService.getDogsList();
  }

  deleteDog(id: number) {
    this.dogService.deleteDog(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  dogDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateDog(id: number){
    this.router.navigate(['update', id]);
  }
}
