import { AddressService } from '../address.service';
import { Address } from '../address';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormControl } from "@angular/forms";

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
    private router: Router,private formBuilder: FormBuilder ) {
    this.submitted = false;
    this.error = false;
  }

  ngOnInit(): void {
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
