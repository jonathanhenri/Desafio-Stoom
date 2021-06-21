import { AddressDetailsComponent } from './details-address/address-details.component';
import { CreateAddressComponent } from './create-address/create-address.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddressListComponent } from './list-address/address-list.component';
import { UpdateAddressComponent } from './update-address/update-address.component';

const routes: Routes = [
  { path: '', redirectTo: 'address', pathMatch: 'full' },
  { path: 'address', component: AddressListComponent },
  { path: 'add', component: CreateAddressComponent },
  { path: 'update/:id', component: UpdateAddressComponent },
  { path: 'details/:id', component: AddressDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
