import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { CustomerFormComponent } from './customer-form/customer-form.component';
import { MainPageComponent } from './main-page/main-page.component';
import { VehicleDetailsComponent } from './vehicle-details/vehicle-details.component';


const routes: Routes = [
  {path: "", redirectTo: '/Home', pathMatch: 'full'},
  {path: "Home", component: MainPageComponent},
  {path: "vehicles", component: VehiclesComponent},
  {path: "vehicles/:plateNo", component: VehicleDetailsComponent},
  {path: "vehicles/:plateNo/customer-form", component: CustomerFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponent = [MainPageComponent, VehiclesComponent, CustomerFormComponent, VehicleDetailsComponent];
