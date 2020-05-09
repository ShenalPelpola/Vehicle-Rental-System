package lk.shenal.CourseWorkphase3;

import com.mongodb.*;
import lk.shenal.CourseWorkphase3.model.*;
import lk.shenal.CourseWorkphase3.services.MongoConnection;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class WestminsterRentalVehicleManager implements RentalVehicleManager {
    private DB dbs = MongoConnection.connect();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static final int MAX_PARKING_LOTS = 50;



    private void displayActions(){
        String name = "WESTMINSTER VEHICLE RENTAL MANAGER" + '\n' + "----------------------------------";
        String message = "Menu options: " + '\n' + '\n'+
                         "* Enter 'Add' to add a new Vehicle" + "\n" +
                         "* Enter 'Delete' to delete a Vehicle" + "\n" +
                         "* Enter 'Print' to show all the Vehicles" + "\n" +
                         "* Enter 'Save' to save all the Vehicles in a text file" + "\n" +
                         "* Enter 'change image' to set a new image" + "\n" +
                         "* Enter 'Open Gui' to open the user interface" + "\n" +
                         "* Enter 'exit' if you want to exit the program.";
        System.out.println();
        System.out.println();
        System.out.println(name + '\n');
        System.out.println(message);
    }


    public boolean runConsoleMenu() throws IOException {
        System.out.print("\n" + "Enter your option: ");
        boolean exit = false;
        Prompter prompter = new Prompter();

        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        if(option.equalsIgnoreCase("Add")) {
            String plateNo = prompter.promptForPlateNo();
            String make = prompter.promptForMake();
            String model = prompter.promptForModel();
            String color = prompter.promptForColor();
            String transmissionType = prompter.promptForTransmissionType();
            BigDecimal rent = prompter.promptForRentPerDay();
            int engineCapacity = prompter.promptForEngineCapacity();
            System.out.println();

            String vehicleType = prompter.promptForVehicle();
            if (vehicleType.equalsIgnoreCase("car")) {
                boolean airCondition = prompter.promptForAirCondition();
                boolean airBagProtection = prompter.promptForAirBagProtection();
                Car car = new Car(plateNo, make, model, color, transmissionType, engineCapacity, rent,"" , airCondition, airBagProtection);
                this.addNewVehicle(car);
            }
            else if (vehicleType.equalsIgnoreCase("bike")) {
                int numberOfHelmets = prompter.promptForNumberOfHelmets();
                int numberOfJackets = prompter.promptForNumberOfJackets();
                Motorbike motorbike = new Motorbike(plateNo, make, model, color, transmissionType, engineCapacity, rent, "", numberOfHelmets, numberOfJackets);
                this.addNewVehicle(motorbike);
            }
        }
        else if(option.equalsIgnoreCase("print")){
            this.printListOfVehicles();
        }
        else if(option.equalsIgnoreCase("delete")){
            this.deleteVehicle();
        }
        else if(option.equalsIgnoreCase("save")){
            this.saveListOfVehicles();
        }
        else if(option.equalsIgnoreCase("change image")){
            this.changeImage();
        }
        else if(option.equalsIgnoreCase("exit")){
            exit = true;
        }
        else {
            this.displayActions();
        }
        return exit;
    }

    /**Add Method*
        The following method adds an vehicle object to the list add the mongodb database
        If the parking slots are <= 50 then the adding will take place.
        Else it will print "sorry the parking has reached the max capacity od 50."
        Every time a vehicle is added "saveListOfVehicles" method will be run.
     **/
    @Override
    public boolean addNewVehicle(Vehicle vehicle) throws IOException {
        //Getting the two collections "Cars" and "Motor bikes" from the "VehicleRental" database.
        DBCollection cars = dbs.getCollection("Cars");
        DBCollection motorBikes = dbs.getCollection("Motor bikes");
        boolean added = false;

        //creating a BasicDBObject for inserting to the database.
        BasicDBObject document = new BasicDBObject();

        if(vehicles.size() <= MAX_PARKING_LOTS) {
            vehicles.add(vehicle);
            //If the add vehicle is a car it will add to the 'Cars' collection in the database.
            if(vehicle instanceof Car){
                //adding the field to the document
                addGeneralFields(vehicle, document);
                document.put("HasAirCondition",((Car) vehicle).isAirCondition());
                document.put("HasAirBagProtection",((Car) vehicle).isAirBagProtection());
                cars.insert(document);
                added = true;
                //If the add vehicle is a motor bike it will add to the 'motorBike' collection in the database.
            }else if(vehicle instanceof Motorbike){
                //adding the field to the document
                addGeneralFields(vehicle, document);
                document.put("numberOfHelmets",((Motorbike) vehicle).getNumOfHelmets());
                document.put("numberOfJackets",((Motorbike) vehicle).getNumOfJackets());
                motorBikes.insert(document);
                added = true;
            }
            System.out.println('\n'+ "* A new vehicle is ADDED!!!");
            this.saveListOfVehicles();
        }else {
            System.out.println("sorry the parking has reached the max capacity od 50.");
            added = false;
        }
        this.loadCollectionToList();
        System.out.println(vehicles.size());
        return added;
    }

    /**
     *Add common attributes to the document.
     */
    private void addGeneralFields(Vehicle vehicle, BasicDBObject document) {
        document.put("plateNo",vehicle.getPlateNo());
        document.put("make",vehicle.getMake());
        document.put("model",vehicle.getModel());
        document.put("color",vehicle.getColor());
        document.put("transmissionType",vehicle.getTransmissionType());
        document.put("engineCapacity",vehicle.getEngineCapacity());
        document.put("rent",vehicle.getRentPerDay());
        document.put("imageUrl", vehicle.getImageUrl());
    }

    /***
     * This method will return a boolean.
     * Checks whether a curtain document is available before deleting.
     * The method basically loops through the entire collection to find the document with the specified condition(whereQuery)
     */
    private boolean findAndDisplayRemovedRecord(DBCollection collection, BasicDBObject whereQuery) {
        boolean found = false;
        for(DBObject obj : collection.find(whereQuery)){
            if(obj != null) {
                found = true;
                System.out.println("The following vehicle has been deleted.");
                System.out.println("* PlateNo: " + obj.get("plateNo"));
                System.out.println("* Make: " + obj.get("make"));
                System.out.println("* Color: " + obj.get("color"));
                System.out.println("* TransmissionType: " + obj.get("transmissionType"));
            }
        }
        return found;
    }

    @Override
    public void deleteVehicle() throws IOException {
        DBCollection cars = dbs.getCollection("Cars");
        DBCollection motorBikes = dbs.getCollection("Motor bikes");
        boolean exit;
        do {
            exit = true;
            BasicDBObject whereQuery = new BasicDBObject();

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the plate number of the vehicle you want the delete: ");
            String plate_no = sc.nextLine();

            whereQuery.put("plateNo", plate_no);
            boolean carAvailable = findAndDisplayRemovedRecord(cars, whereQuery);
            boolean motorBikeAvailable = findAndDisplayRemovedRecord(motorBikes, whereQuery);
            if (!(carAvailable || motorBikeAvailable)) {
                System.out.printf("Vehicle with the plate number of %s is not available.%n%n", plate_no);
                exit = false;
            }else if(carAvailable){
                System.out.println("* The type of the vehicle: Car");
                cars.remove(whereQuery);
            }else {
                System.out.println("* The type of the vehicle: Motor bike");
                motorBikes.remove(whereQuery);
            }
        }while(!exit);
        this.saveListOfVehicles();
    }

    /**
     *The method loops through every collection and assign every field to an new object(of type car or motorbike) in to an Arraylist.
     * The method return a new Arraylist and updates the 'vehciels' Arraylist every time addNewVehicle ,deleteVehicle(), print or save is called.
     * The method basically refreshers the "vehicles" Arraylist every time the method is called.
     */
    public ArrayList<Vehicle> loadCollectionToList(){
        //Creating a new arraylist which is scoped to this method to Store data from documents.
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        //Getting to collections from the database
        DBCollection cars = dbs.getCollection("Cars");
        DBCollection motorBikes = dbs.getCollection("Motor bikes");

        /**Loop through the "cars" or "motor bikes" collections and retrieves the document.
         * Create a new a new Car or MotorBike add it in to the new Arraylist.
        **/
        for(DBObject carDocument : cars.find()){
            BigDecimal carRent = new BigDecimal(carDocument.get("rent").toString());
            Car car = new Car((String)carDocument.get("plateNo"),(String)carDocument.get("make"),
                    (String)carDocument.get("model"),(String) carDocument.get("color"),
                    (String) carDocument.get("transmissionType"), (Integer) carDocument.get("engineCapacity"),
                    carRent,(String)carDocument.get("imageUrl"), (Boolean) carDocument.get("HasAirCondition"),
                    (Boolean) carDocument.get("HasAirBagProtection")

            );
            vehicles.add(car);
        }
        for(DBObject motorBikeDocument : motorBikes.find()){
            BigDecimal bikeRent = new BigDecimal(motorBikeDocument.get("rent").toString());
            Motorbike motorbike = new Motorbike((String)motorBikeDocument.get("plateNo"),(String)motorBikeDocument.get("make"),
                    (String)motorBikeDocument.get("model"),(String) motorBikeDocument.get("color"), (String) motorBikeDocument.get("transmissionType"),
                    (int)motorBikeDocument.get("engineCapacity"),bikeRent, (String)motorBikeDocument.get("imageUrl") , (Integer) motorBikeDocument.get("numberOfHelmets"),
                    (int)motorBikeDocument.get("numberOfJackets")
            );
            vehicles.add(motorbike);
        }
        return vehicles;
    }

    /**
     *  Checks whether the Arraylist of vehicles is empty.
     *  if it if not Arraylist is sorted based on the make of the vehicle.
     *  Finally loops thought the entire arraylist and prints every vehicle object to the console.
      **/
    @Override
    public void printListOfVehicles() {
        vehicles = loadCollectionToList();
        if(vehicles.isEmpty()){
            System.out.println("==> There are no vehicles currently..Enter 'Add' as an option if you want to add any.<==" + "\n");
        }
        Collections.sort(vehicles);
        int index = 1;
        for(Vehicle vehicle : vehicles){
            System.out.println(index + ")" + vehicle);
            index += 1;
        }
    }

    /**
     * Sorting the arraylist based on the make of the vehicle.
     * Writting every obect in the "vehicles" arraylist as a string to a filenamed "Vehicles.txt"
     * **/
    @Override
    public void saveListOfVehicles() throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream("Vehicles.txt"));
        //Emptying the file before rewritting.
        out.writeUTF("");
        vehicles = loadCollectionToList();
        Collections.sort(vehicles);
        for(Vehicle vehicle : vehicles) {
            out.writeUTF(vehicle.toString() + "\n");
        }
        System.out.println("==> List of vehicles have been saved!! <==");
    }

    public void changeImage(){
        Prompter prompter = new Prompter();
        vehicles = this.loadCollectionToList();
        DBCollection cars = dbs.getCollection("Cars");
        DBCollection motorBikes = dbs.getCollection("Motor bikes");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the vehicle plate number of the vehicle: ");
        String plateNo = prompter.promptForPlateNo();

        System.out.print("Enter what you want to change is a bike or a car: ");
        String vehicleType = prompter.promptForVehicle();

        for(Vehicle vehicle : vehicles){
            if(vehicle.getPlateNo().equals(plateNo) && vehicle.getPlateNo() != null){
                BasicDBObject query = new BasicDBObject();
                query.put("imageUrl", vehicle.getImageUrl());
                System.out.println(vehicle.getImageUrl());

                System.out.print("Enter the file name of the image: ");
                String imageName = sc.nextLine();

                System.out.print("Enter the file extension of the image: ");
                String imageExtension = sc.nextLine();

                String newImage = "../../assets/images/" + imageName + "." + imageExtension;

                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("imageUrl", newImage);

                BasicDBObject updateObject = new BasicDBObject();
                updateObject.put("$set", newDocument);

                if(vehicleType.equalsIgnoreCase("car")) {
                    cars.update(query, updateObject);
                }else if (vehicleType.equalsIgnoreCase("bike")){
                    motorBikes.update(query,updateObject);
                }
            }
        }
        vehicles = this.loadCollectionToList();
    }



    public static void main(String[] args) throws IOException {
        WestminsterRentalVehicleManager rentalVehicleManager = new WestminsterRentalVehicleManager();
        rentalVehicleManager.displayActions();
        boolean exit = false;

        while (!exit){
            exit = rentalVehicleManager.runConsoleMenu();
        }
    }
}


