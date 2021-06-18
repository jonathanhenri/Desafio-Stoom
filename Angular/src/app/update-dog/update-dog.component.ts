import { Component, OnInit } from '@angular/core';
import { Dog } from '../dog';
import { ActivatedRoute, Router } from '@angular/router';
import { DogService } from '../dog.service';

@Component({
  selector: 'app-update-dog',
  templateUrl: './update-dog.component.html',
  styleUrls: ['./update-dog.component.css']
})
export class UpdateDogComponent implements OnInit {

  id: number;
  dog: Dog;

  constructor(private route: ActivatedRoute,private router: Router,
    private dogService: DogService) { }

  ngOnInit() {
    this.dog = new Dog();

    this.id = this.route.snapshot.params['id'];

    this.dogService.getDog(this.id)
      .subscribe(data => {
        console.log(data);
        this.dog = data;
      }, error => console.log(error));
  }

  updateDog() {
    this.dogService.updateDog(this.id, this.dog)
      .subscribe(data => {
        console.log(data);
        this.dog = new Dog();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateDog();
  }

  gotoList() {
    this.router.navigate(['/dogs']);
  }
}
