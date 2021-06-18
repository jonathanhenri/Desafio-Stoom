import { Dog } from '../dog';
import { Component, OnInit, Input } from '@angular/core';
import { DogService } from '../dog.service';
import { DogListComponent } from '../dog-list/dog-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dog-details',
  templateUrl: './dog-details.component.html',
  styleUrls: ['./dog-details.component.css']
})
export class DogDetailsComponent implements OnInit {

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

  list(){
    this.router.navigate(['dogs']);
  }
}
