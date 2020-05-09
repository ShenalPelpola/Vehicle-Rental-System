package lk.shenal.CourseWorkphase3;

import lk.shenal.CourseWorkphase3.model.Vehicle;
import org.springframework.stereotype.Service;


import java.io.IOException;

public interface RentalVehicleManager {

    boolean addNewVehicle(Vehicle vehicle) throws IOException;
      void deleteVehicle() throws IOException;
    void printListOfVehicles();
    void saveListOfVehicles() throws IOException;
}
