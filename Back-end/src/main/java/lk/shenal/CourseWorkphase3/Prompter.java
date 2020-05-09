package lk.shenal.CourseWorkphase3;

import lk.shenal.CourseWorkphase3.model.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Prompter {
    private Scanner sc = new Scanner(System.in);
    private WestminsterRentalVehicleManager westminsterRentalVehicleManager = new WestminsterRentalVehicleManager();

    public String promptForPlateNo() {
        String plateNo;
        while (true) {
            System.out.print("1. Enter the plate number of the vehicle: ");
            plateNo = sc.nextLine();
            if (plateNo.replaceAll(" ", "").length() == 0) {
                System.out.println("Error ==> plate number can not be null..Please enter a valid value.");
                System.out.println("Please try again.");
            } else if (checkAvailabilityOfaVehicle(plateNo)) {
                System.out.println("Error ==> A vehicle with that plate number already exits.");
                System.out.println("Please try again.");
            } else {
                break;
            }
        }
        return plateNo;
    }

    private boolean checkAvailabilityOfaVehicle(String plateNo){
        List<Vehicle> vehicles = westminsterRentalVehicleManager.loadCollectionToList();
        for(Vehicle vehicle : vehicles){
            if(vehicle.getPlateNo().equals(plateNo)){
                return true;
            }
        }
        return false;
    }

    public String promptForMake(){
        System.out.print("2. Enter the make of the vehicle: ");
        String make = sc.nextLine();
        return make;
    }
    public String promptForModel(){
        System.out.print("3. Enter the model of the vehicle: ");
        return sc.nextLine();
    }

    public String promptForColor(){
        System.out.print("4. Enter the color of the vehicle: ");
        String color = sc.nextLine();
        return color;
    }

    public String promptForTransmissionType(){
        System.out.print("5. Enter the transmission type of the vehicle: ");
        String transmissionType = sc.nextLine();
        return  transmissionType;
    }

    public BigDecimal promptForRentPerDay(){
        BigDecimal rent;
        while (true) {
            try {
                System.out.print("6. Enter the rent per day for the vehicle: ");
                rent = new BigDecimal(sc.next());
                break;
            }catch (Exception e){
                System.out.println("Error ==> Vehicle rent should be a numeric value..sorry the value could'nt be entered." );
                System.out.println("Please try again..");
                sc.nextLine();
                System.out.println();
            }
        }
        return rent;
    }

    public int promptForEngineCapacity(){
        int engineCapacity;
        while (true) {
            try {
                System.out.print("7. Enter the engine capacity for the vehicle: ");
                engineCapacity = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Error ==> Vehicle rent should be a numeric value..sorry the value could'nt be entered.");
                System.out.println("Please try again..");
                sc.nextLine();
                System.out.println();
            }
        }
        return engineCapacity;
    }

    public String promptForVehicle(){
        String vehicleType;
        while(true) {
            System.out.print("Enter the vehicle is a Car or a motor bike: ");
            vehicleType = sc.next();
            if(vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("bike")){
                break;
            }else{
                System.out.println("Error ==> Vehicle should be either a car or a bike.");
                System.out.println("Please try again..");
            }
        }
        return vehicleType;
    }


    public boolean promptForAirCondition(){
        boolean airCondition;
        while (true) {
            try {
                System.out.print("8. Enter whether any air condition is available?(true/false): ");
                airCondition = sc.nextBoolean();
                break;
            } catch (Exception e) {
                System.out.println("Error ==> This should be a either true or false.");
                sc.nextLine();
                System.out.println();
            }
        }
        return airCondition;
    }

    public boolean promptForAirBagProtection(){
        boolean airBagProtection;
        while(true) {
            try {
                System.out.print("9. Enter whether any air bag protection is available?(true/false): ");
                airBagProtection = sc.nextBoolean();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value.");
                sc.nextLine();
                System.out.println();
            }
        }
        return airBagProtection;
    }

    public int promptForNumberOfHelmets(){
        int numberOfHelmets;
        while (true) {
            try {
                System.out.print("8. Enter the number of helmets available: ");
                numberOfHelmets = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid value.");
                sc.nextLine();
                System.out.println();
            }
        }
        return numberOfHelmets;
    }

    public int promptForNumberOfJackets(){
        int numberOfJackets;
        while (true) {
            try {
                System.out.print("9. Enter the number of Jackets available: ");
                numberOfJackets = sc.nextInt();
                break;
            }catch (Exception e){
                System.out.println("Please enter a valid value.");
                sc.nextLine();
                System.out.println();
            }
        }
        return numberOfJackets;
    }



}
