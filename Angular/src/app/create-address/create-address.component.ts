import { AddressService } from '../address.service';
import { Address } from '../address';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-address',
  templateUrl: './create-address.component.html',
  styleUrls: ['./create-address.component.css']
})
export class CreateAddressComponent implements OnInit {

  address: Address = new Address();
  submitted = false;
  error = false;

  constructor(private addressService: AddressService,
    private router: Router) { }

  ngOnInit(): void {
    // this = new FormGroup({
    //   name: new FormControl(this.address.streetName, [
    //     Validators.required
    //   ]),
    //   // alterEgo: new FormControl(this.hero.alterEgo),
    //   // power: new FormControl(this.hero.power, Validators.required)
    // });

  }

  save() {
    this.addressService
    .createAddress(this.address).subscribe(data => {
      this.address = new Address();
      this.gotoList();
    },
    error => {
      this.submitted = false, this.error = true;
    });
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/address']);
  }
}
