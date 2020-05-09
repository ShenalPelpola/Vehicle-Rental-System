package lk.shenal.CourseWorkphase3.model;
import java.math.BigDecimal;

public class Motorbike extends Vehicle {
    private int numOfHelmets;
    private int numOfJackets;

    public Motorbike(String plateNo, String make, String model, String color, String transmissionType, int engineCapacity, BigDecimal rentPerDay, String imageUrl, int numOfHelmets, int numOfJackets) {
        super(plateNo, make, model, color, transmissionType, engineCapacity, rentPerDay, imageUrl);
        this.numOfHelmets = numOfHelmets;
        this.numOfJackets = numOfJackets;
    }

    public int getNumOfHelmets() {
        return numOfHelmets;
    }

    public void setNumOfHelmets(int numOfHelmets) {
        this.numOfHelmets = numOfHelmets;
    }

    public int getNumOfJackets() {
        return numOfJackets;
    }

    public void setNumOfJackets(int numOfJackets) {
        this.numOfJackets = numOfJackets;
    }

    @Override
    public String toString() {
        return super.toString() +
                "  * NumOfHelmets: " + numOfHelmets + '\n' +
                "  * NumOfJackets: " + numOfJackets + '\n';
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.getMake().compareTo(o.getMake());
    }
}
