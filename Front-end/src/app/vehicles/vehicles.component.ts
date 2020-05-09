import { Component, OnInit, ɵConsole } from '@angular/core';
import { VehiclesService } from '../services/vehicles service/vehicles.service';
import {Router } from '@angular/router';
import { Vehicle } from '../models/Vehicle';
import { Car } from '../models/Car';
import { Motorbike } from '../models/Motorbike';




@Component({
  selector: 'vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {
  public cars = [];
  public vehicles : Vehicle[];
  public motorBikes = [];
  public items = [];
  public isCollapsed : boolean = true;
  private _searchTerm: string;
  filteredCars = [];
  fitleredBikes = [];
  fileToUpload: File;
  private preferance: string;

  constructor(private service : VehiclesService, private router : Router){}
 

  ngOnInit() {
    this.service.getVehicles().subscribe(res => {
    this.vehicles = res;
    this.assignVehicles();
    this.filteredCars = this.cars;
    this.fitleredBikes = this.motorBikes;
    })
  }

  assignVehicles(){
    for(let vehicle of this.vehicles){
      if(vehicle.hasOwnProperty("airCondition")){
       this.cars.push(<Car>vehicle);
      }
      else if(vehicle.hasOwnProperty("numOfHelmets")){
        this.motorBikes.push(<Motorbike>vehicle);
      }
    }
  }
  
   get searchTerm(): string{
    return this._searchTerm;
  }

  set searchTerm(value : string){
    this._searchTerm = value;
    this.filteredCars = this.filterCars(value);
    this.fitleredBikes = this.filterBikes(value);
  }

  filterCars(searchString : string){
    return this.cars.filter(car =>
      car.make.toLowerCase().indexOf(searchString.toLowerCase()) !== -1);
  }

  filterBikes(searchString : string){
    return this.motorBikes.filter(bike =>
      bike.make.value.toLowerCase().indexOf(searchString.toLowerCase()) !== -1);
  }

  getSearchPreference(event : any){
    this.preferance = event.target.value;
  }  

  showListOfCar(){
      document.getElementById("car-button").style.backgroundColor = "black";
      document.getElementById("car-button").style.color = "white";
      document.getElementById("bike-button").style.backgroundColor = "white";
      document.getElementById("bike-button").style.color = "black";
      document.getElementById("car-container").style.display = "block";
      document.getElementById("bike-container").style.display = "none";
  }

  showListOfMotorBikes(){
    document.getElementById("bike-button").style.backgroundColor = "black";
    document.getElementById("bike-button").style.color = "white";
    document.getElementById("car-button").style.backgroundColor = "white";
    document.getElementById("car-button").style.color = "black";
    document.getElementById("bike-container").style.display = "block";
    document.getElementById("car-container").style.display = "none";
  }

  onSelectView(vehicle){
   this.router.navigate(['/vehicles', vehicle.plateNo]);
  }
  onSelectForm(vehicle){
    this.router.navigate(['/vehicles', vehicle.plateNo, 'customer-form']);
   }
}
