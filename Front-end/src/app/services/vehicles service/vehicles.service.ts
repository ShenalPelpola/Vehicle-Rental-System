import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Vehicle } from 'src/app/models/Vehicle';


@Injectable({
  providedIn: 'root'
})
export class VehiclesService {
  private _url : string = "api/vehicles";
  private vehicles : Vehicle[];
  private cars = [];
  private motorBikes = [];


  constructor(private http : HttpClient) {
  }

  getVehicles() : Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(this._url); 
  }
  
}
