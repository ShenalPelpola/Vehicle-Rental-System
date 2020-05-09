package lk.shenal.CourseWorkphase3.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Vehicle implements Comparable<Vehicle>{
    private String plateNo;
    private String make;
    private String model;
    private String color;
    private String transmissionType;
    private int engineCapacity;
    private BigDecimal rentPerDay;
    private String imageUrl;


    public Vehicle(String plateNo, String make, String model, String color, String transmissionType, int engineCapacity, BigDecimal rentPerDay, String imageUrl) {
        this.plateNo = plateNo;
        this.make = make;
        this.model = model;
        this.color = color;
        this.transmissionType = transmissionType;
        this.engineCapacity = engineCapacity;
        this.rentPerDay = rentPerDay;
        this.imageUrl = imageUrl;
    }

    public String getPlateNo() {
        return plateNo;
    }


    public String getMake() {
        return make;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }


    public String getTransmissionType() {
        return transmissionType;
    }


    public int getEngineCapacity() {
        return engineCapacity;
    }


    public BigDecimal getRentPerDay() {
        return rentPerDay;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "* PlateNo: " + plateNo + '\n' +
                "  * Make: " + make + '\n' +
                "  * Model: " + model + '\n' +
                "  * Color: " + color + '\n' +
                "  * TransmissionType: " + transmissionType + '\n' +
                "  * EngineCapacity: " + engineCapacity + '\n' +
                "  * RentPerDay: " + rentPerDay + '\n' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return engineCapacity == vehicle.engineCapacity &&
                Objects.equals(plateNo, vehicle.plateNo) &&
                Objects.equals(make, vehicle.make) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(color, vehicle.color) &&
                Objects.equals(transmissionType, vehicle.transmissionType) &&
                Objects.equals(rentPerDay, vehicle.rentPerDay) &&
                Objects.equals(imageUrl, vehicle.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNo, make, model, color, transmissionType, engineCapacity, rentPerDay, imageUrl);
    }


}
