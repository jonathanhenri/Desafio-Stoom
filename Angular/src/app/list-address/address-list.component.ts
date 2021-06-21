import { AddressDetailsComponent } from '../details-address/address-details.component';
import { Observable } from "rxjs";
import { AddressService } from "../address.service";
import { Address } from "../address";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-address-list",
  templateUrl: "./address-list.component.html",
  styleUrls: ["./address-list.component.css"]
})
export class AddressListComponent implements OnInit {
  addresss: Observable<Address[]>;

  constructor(private addressService: AddressService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.addresss = this.addressService.getAddressList();
  }

  deleteAddress(id: number) {
    this.addressService.deleteAddress(id)
      .subscribe(
        data => {
          this.reloadData();
        },
        error => console.log(error));
  }

  addressDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateAddress(id: number){
    this.router.navigate(['update', id]);
  }
}
