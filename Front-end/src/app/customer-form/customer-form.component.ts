import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/Customer';
import { CustomerServiceService } from '../services/customer-service.service';
import { VehiclesService } from '../services/vehicles service/vehicles.service';
import { Vehicle } from '../models/Vehicle';
import { ActivatedRoute, Router } from '@angular/router';
declare var jQuery:any;
declare var $: any;

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {
  customer : Customer;
  public vehiclePlateNo;
  private vehicles : Vehicle[];
  private selectedVehicle;
  constructor(private vehicleService : VehiclesService, private service: CustomerServiceService, private route: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    //setting the display value to hide
    $('#flashMessage').hide()
    let plateNo = this.route.snapshot.paramMap.get('plateNo');
    this.vehiclePlateNo = plateNo;
    //creating a new customr id with empty fields to be filled when registerNow() is cliked
    this.customer =  new Customer("", "" , "" , "" , "", "","","", plateNo);
    this.vehicleService.getVehicles().subscribe(res => {
      this.vehicles = res;
      this.selectedVehicle = this.selectByPlateNo();
    });
  }

  //when the submit button is clicked the doCustomerRegis
  public registerNow(){
    let response = this.service.doCustomerRegister(this.customer);
    response.subscribe(
       response => console.log("Success!" , response),
       error => console.error("error!", console.error())
    );
  }

  //Displaying the confirmation message 1s after submission
  public displaySuccessMessage(){
    $("#flashMessage")
    .delay(1000)
    .slideDown(1000)
    .delay(4000)
    .slideUp();
    setTimeout(() => {
      this.router.navigate(['Home']);
    }, 7000);
  }

  //loop through the vehicles list and select the vehicle by the plate number.
  selectByPlateNo(){
    for(let vehicle of this.vehicles){
      if(vehicle.hasOwnProperty("plateNo")){
        if(vehicle.plateNo == this.vehiclePlateNo){
          return vehicle;
        }
      }
    }
  }

}
