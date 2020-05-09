import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router } from '@angular/router';
import { VehiclesService } from '../services/vehicles service/vehicles.service';
import { Vehicle } from '../models/Vehicle';
import { Car } from '../models/Car';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {

  public vehiclePlateNo;
  private vehicles : Vehicle[];
  private selectedVehicle;
 
  constructor(private route: ActivatedRoute, private service : VehiclesService, private router : Router) { }

  ngOnInit() {
    let plateNo = this.route.snapshot.paramMap.get('plateNo');
    this.vehiclePlateNo = plateNo;
    this.service.getVehicles().subscribe(res => {
      this.vehicles = res;
      this.selectedVehicle = this.selectByPlateNo();
    });
  } 


  selectByPlateNo(){
    for(let vehicle of this.vehicles){
      if(vehicle.hasOwnProperty("plateNo")){
        if(vehicle.plateNo == this.vehiclePlateNo){
          return vehicle;
        }
      }
    }
  }

  onSelectForm(){
    this.router.navigate(['/vehicles',this.vehiclePlateNo, 'customer-form']);
   }

}
