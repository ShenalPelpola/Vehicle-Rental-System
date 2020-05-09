import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule, routingComponent} from './app-routing.module';
import { AppComponent} from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {HttpClientModule} from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { VehiclesService } from './services/vehicles service/vehicles.service';
import {FormsModule} from '@angular/forms';
import { VehicleDetailsComponent } from './vehicle-details/vehicle-details.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    routingComponent,
    VehicleDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule
  ], 
  providers: [
    VehiclesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
