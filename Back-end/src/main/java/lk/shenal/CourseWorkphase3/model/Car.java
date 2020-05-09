package lk.shenal.CourseWorkphase3.model;
import java.math.BigDecimal;

public class Car extends Vehicle {
    private boolean airCondition;
    private boolean airBagProtection;


    public Car(String plateNo, String make, String model, String color, String transmissionType, int engineCapacity, BigDecimal rentPerDay, String imageUrl, boolean airCondition, boolean airBagProtection) {
        super(plateNo, make, model, color, transmissionType, engineCapacity, rentPerDay, imageUrl);
        this.airCondition = airCondition;
        this.airBagProtection = airBagProtection;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public boolean isAirBagProtection() {
        return airBagProtection;
    }

    public void setAirBagProtection(boolean airBagProtection) {
        this.airBagProtection = airBagProtection;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "  * AirCondition: " + airCondition + '\n' +
                "  * AirBagProtection: " + airBagProtection + '\n';
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }
}
