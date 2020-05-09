package lk.shenal.CourseWorkphase3.services;

import com.mongodb.*;
import lk.shenal.CourseWorkphase3.model.Customer;
import lk.shenal.CourseWorkphase3.model.Schedule;
import lk.shenal.CourseWorkphase3.model.Vehicle;
import org.bson.Document;
import java.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


@Service
public class CustomerService implements CustomerServiceInterface{
    private MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    private DB dbs = mongoClient.getDB("VehicleRental");
    DBCollection cars = dbs.getCollection("Cars");
    DBCollection motorBikes = dbs.getCollection("Motor bikes");
    @Autowired
    private VehicleService vehicleService;


    public void addCustomer(Customer customer){
        DBCollection customers = dbs.getCollection("customers");
        BasicDBObject document = new BasicDBObject();
        document.put("customerId",customer.getCustomerId());
        document.put("customerName",customer.getCustomerName());
        document.put("customerAddress",customer.getCustomerAddress());
        document.put("customerEmail", customer.getCustomerEmail());
        document.put("telNo",customer.getTelNo());
        document.put("licenceNo",customer.getLicenceNo());
        document.put("Ordered vehicle plateNo", customer.getPlateNo());
        document.put("pickUpDate", customer.getPickUpDate());
        document.put("dropOfDate",customer.getDropOfDate());
        customers.insert(document);
    }

    public String generateCustomerId(Customer customer){
        Random rand = new Random();
        String randomNumber = Integer.toString(rand.nextInt(1000));
        return randomNumber + "_" + customer.getLicenceNo();
    }

    public BigDecimal calculateRent(Customer customer){
        Period intervalPeriod = Period.between(customer.getPickUpDate(), customer.getDropOfDate());
        int difference = intervalPeriod.getDays();
        Vehicle vehicle = vehicleService.getVehicle(customer.getPlateNo());
        BigDecimal rentPerDay = vehicle.getRentPerDay();
        BigDecimal totalRent = rentPerDay.multiply(BigDecimal.valueOf(difference));
        return  totalRent;
    }
}

