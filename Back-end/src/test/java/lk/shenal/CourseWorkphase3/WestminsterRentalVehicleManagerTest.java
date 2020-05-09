package lk.shenal.CourseWorkphase3;

import lk.shenal.CourseWorkphase3.model.Car;
import lk.shenal.CourseWorkphase3.model.Motorbike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterRentalVehicleManagerTest {
    private WestminsterRentalVehicleManager westminsterRentalVehicleManager;
    @BeforeEach
    void setUp() {
        westminsterRentalVehicleManager = new WestminsterRentalVehicleManager();
    }


    @Test
    void addNewVehicle() throws IOException {
        westminsterRentalVehicleManager.loadCollectionToList().size();
        Car car = new Car("223","sdcs","scdc","weew",
                "wdw",2322,new BigDecimal("2332"),"",true,true);
        Motorbike bike = new Motorbike("223","sdcs","scdc","weew",
                "wdw",2322,new BigDecimal("2332"),"",3,4);
        assertTrue(westminsterRentalVehicleManager.addNewVehicle(car));
        assertTrue(westminsterRentalVehicleManager.addNewVehicle(bike));
    }
}
