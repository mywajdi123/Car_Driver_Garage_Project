package classPackage;

import java.util.Scanner;

public class Garage {
    private static final Scanner input = new Scanner(System.in);
    private final Car[] parking;
    private Car currentCar;

    public Garage(int numOfCars) {
        this.currentCar = null;
        this.parking = new Car[numOfCars];
    }

    public void park(Car car, int spaceNumber) {
        if (spaceNumber >= 0 && spaceNumber < parking.length - 1) {
            parking[spaceNumber] = car;
        }
    }

    public void displayOptions() {
        System.out.println("\t  A) Refuel a car\n" +
                "\t  B) Get a Car to drive\n" +
                "\t  C) Add a Car\n" +
                "\t  Q) Quit this garage\n");

        char choice = input.next().charAt(0);
        menuChoice(Character.toUpperCase(choice));
    }

    private void menuChoice(char choice) {
        switch (choice) {
            case 'A':
                refuel();
                break;
            case 'B':
                drive();
                break;
            case 'C':
                addCar();
                break;
            case 'Q':
                System.out.println("Thank you for using this garage!");
                System.exit(0);
            default:
                System.out.println("Invalid menu choice");
        }


        do {
            System.out.println("Would you like to repeat this program?");
            choice = input.next().charAt(0);
            choice = Character.toLowerCase(choice);
            if (choice != 'y' && choice != 'n') {
                System.out.println("Please enter \"y\" for yes or \"n\" for no.");
            }
        } while (choice != 'y' && choice != 'n');

        if (choice == 'y') {
            displayOptions();
        } else {
            System.out.println("Good-bye!");
            System.exit(0);
        }
    }

    private void addCar() {
        System.out.println("Do you want to add random data or manual? \n\tA) Random\n\tB) Manual");
        char choice = Character.toUpperCase(input.next().charAt(0));

        if (choice != 'A' && choice != 'B') {
            System.out.println("Invalid Input Provided!");
            return;
        }

        Car car;

        if (choice == 'A') {
            car = new Car();
        } else {

            int year = (int) this.getValidatedValue("Please enter a year between 1920 and 2021: ", "Please enter between 1920 and 2021 only.", 1920, 2021);
            System.out.print("Please enter model:");
            String model = input.next();
            System.out.print("Please enter make:");
            String make = input.next();
            System.out.print("Please enter color:");
            String color = input.next();
            double fuelTankSize = this.getValidatedValue("Please enter fuel tank size: ", "Enter a value between 8.0 and 34.99.", 8.0, 34.99);
            double optimalSpeed = this.getValidatedValue("Please enter optimal speed: ", "Enter a value between 45.0 and 64.99 only.", 45, 64.99);
            double fuelEconomy = this.getValidatedValue("Please enter fuel economy: ", "Enter a value between 15.0 and 54.99 only", 15, 54.99);
            car = new Car(make, model, color, year, fuelTankSize, fuelEconomy, optimalSpeed);
        }

        currentCar = car;

        this.park(car);

    }

    private void park(Car car) {
        for (int i = 0; i < parking.length; i++) {
            if (parking[i] == null) {
                parking[i] = car;
                System.out.println("Car parked at space " + (i + 1));
                return;
            }
        }
        System.out.println("Parking is full");
    }

    public void refuel() {
        System.out.print("Enter Parking Space Number: ");
        int spaceNumber = input.nextInt();

        refuelCar(spaceNumber);
    }

    public void refuelCar(int spaceNumber) {
        if (spaceNumber >= 0 && spaceNumber < parking.length - 1) {
            Car car = parking[spaceNumber];
            if (car != null) {
                System.out.print("How much fuel do you want to add: ");
                double fuel = input.nextDouble();
                car.addFuelToTank(fuel);
            } else {
                System.out.println("There's no park at this space number");
            }
        }
    }

    public void drive() {
        System.out.print("Enter Parking Space Number: ");
        int spaceNumber = input.nextInt();

        currentCar = getCar(spaceNumber);

        if (currentCar != null) {
            System.out.print("Enter Distance to drive: ");
            double distance = input.nextDouble();
            System.out.print("Enter Speed to drive: ");
            double speed = input.nextDouble();

            currentCar.setUpTrip(speed, distance);
            boolean status = currentCar.driveCar();
            System.out.println("Car after driving!!!");
            System.out.println(currentCar);
            if (status) {
                park(currentCar, spaceNumber);
            } else {
                System.out.println("out of gas and lost");
            }
        } else {
            System.out.println("No car at the specified number");
        }
    }

    Car getCar(int spaceNumber) {
        spaceNumber--;
        if (spaceNumber >= 0 && spaceNumber < parking.length - 1) {
            return this.parking[spaceNumber];
        }
        return null;
    }

    private double getValidatedValue(String prompt, String error, double from, double to) {
        double value;
        do {
            System.out.println(prompt);
            value = input.nextDouble();
            if (value < to || value > from) {
                System.out.println(error);
            }
        } while (value < to || value > from);
        return value;
    }

}
