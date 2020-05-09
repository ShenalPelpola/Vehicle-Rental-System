package lk.shenal.CourseWorkphase3.services;

import lk.shenal.CourseWorkphase3.model.Vehicle;

import java.util.List;

public interface VehicleServiceInterface {
    List<Vehicle> getVehiclesFromDB();
    Vehicle getVehicle(String plateNo);
}
