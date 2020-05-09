import { Injectable } from '@angular/core';
import {HttpClient} from  '@angular/common/http';
import { Customer } from '../models/Customer';


@Injectable({
  providedIn: 'root'
})
export class CustomerServiceService {

  constructor(private http : HttpClient) { }

  public doCustomerRegister(customer : Customer){
    console.log(customer);
    return this.http.post("/api/customers" ,customer);
  }
}
