import { DogService } from '../dog.service';
import { Dog } from '../dog';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-dog',
  templateUrl: './create-dog.component.html',
  styleUrls: ['./create-dog.component.css']
})
export class CreateDogComponent implements OnInit {

  dog: Dog = new Dog();
  submitted = false;

  constructor(private dogService: DogService,
    private router: Router) { }

  ngOnInit() {
  }

  newDog(): void {
    this.submitted = false;
    this.dog = new Dog();
  }

  save() {
    this.dogService
    .createDog(this.dog).subscribe(data => {
      console.log(data);
      this.dog = new Dog();
      this.gotoList();
    },
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/dogs']);
  }
}
