package lk.shenal.CourseWorkphase3.controller;
import lk.shenal.CourseWorkphase3.model.Vehicle;
import lk.shenal.CourseWorkphase3.services.VehicleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class VechicleController {
    @Autowired
    private VehicleServiceInterface vehicleService;

    @RequestMapping("api/vehicles")
    public List<Vehicle> getVehicles(){
        return vehicleService.getVehiclesFromDB();
    }

    @RequestMapping("api/vehicles/{plateNo}")
    public Vehicle getVehicles(@PathVariable String plateNo){
        return vehicleService.getVehicle(plateNo);
    }

}
