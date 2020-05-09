package lk.shenal.CourseWorkphase3.services;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.*;
import lk.shenal.CourseWorkphase3.model.Car;
import lk.shenal.CourseWorkphase3.model.Motorbike;
import lk.shenal.CourseWorkphase3.model.Schedule;
import lk.shenal.CourseWorkphase3.model.Vehicle;
import org.bson.Document;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService implements VehicleServiceInterface{
    private MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

    private DB dbs = mongoClient.getDB("VehicleRental");
    private Logger mongoLogger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver");
    public void turnOffLogMessages() {
        mongoLogger.setLevel(Level.ERROR);
    }
    List<Vehicle> vehicles = new ArrayList<>();
    DBCollection cars = dbs.getCollection("Cars");
    DBCollection motorBikes = dbs.getCollection("Motor bikes");


    @Override
    public List<Vehicle> getVehiclesFromDB(){
        List<Vehicle> vehicles = new ArrayList<>();

        /**Loop through the "cars" or "motor bikes" collections and retrieves the document.
         * Create a new a new Car or MotorBike add it in to the new Arraylist.
         **/
        for(DBObject obj1 : cars.find()){
            BigDecimal carRent = new BigDecimal(obj1.get("rent").toString());
            Car car = new Car((String)obj1.get("plateNo"),(String)obj1.get("make"),
                    (String)obj1.get("model"),(String) obj1.get("color"),
                    (String) obj1.get("transmissionType"), (Integer) obj1.get("engineCapacity"),
                    carRent ,(String)obj1.get("imageUrl") , (Boolean) obj1.get("HasAirCondition"),
                    (Boolean) obj1.get("HasAirBagProtection")
            );

            vehicles.add(car);
        }
        for(DBObject obj2 : motorBikes.find()){
            BigDecimal bikeRent = new BigDecimal(obj2.get("rent").toString());
            Motorbike motorbike = new Motorbike((String)obj2.get("plateNo"),(String)obj2.get("make"),
                    (String)obj2.get("model"),(String) obj2.get("color"), (String) obj2.get("transmissionType"),
                    (int)obj2.get("engineCapacity"), bikeRent,(String)obj2.get("imageUrl"), (Integer) obj2.get("numberOfHelmets"),
                    (int)obj2.get("numberOfJackets")
            );
            vehicles.add(motorbike);
        }
        return vehicles;
    }

    @Override
    public Vehicle getVehicle(String plateNo){
        vehicles = this.getVehiclesFromDB();
        for(Vehicle vehicle : vehicles){
            if(vehicle.getPlateNo().equals(plateNo)){
                return vehicle;
            }
        }
        return null;
    }

}
