package classPackage;

public class Car {

    private final String make;
    private final String model;
    private final int year;
    private final double fuelTankSize;
    private final double fuelEconomy;
    private final double optimalSpeed;
    private double odometer;
    private double tripOdometer;
    private String color;
    private double fuelLevel;

    private double speedToTravel;
    private double distanceToTravel;

    public Car() {
        this.make = "TESLA";
        this.model = "Y";
        this.year = 2022;
        this.fuelEconomy = this.generateRandomNumber(15, 55);
        this.optimalSpeed = this.generateRandomNumber(45, 65);
        this.fuelTankSize = this.generateRandomNumber(8, 35);
        this.color = "BLACK";
        this.fuelLevel = 0;
        this.odometer = this.generateRandomNumber(0, 6);
        this.tripOdometer = 0;
    }


    public Car(String make, String model, String color, int year, double fuelTankSize, double fuelEconomy, double optimalSpeed) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelTankSize = fuelTankSize;
        this.fuelEconomy = fuelEconomy;
        this.optimalSpeed = optimalSpeed;
        this.color = color;
        this.fuelLevel = 0;
        this.tripOdometer = 0;
        this.odometer = this.generateRandomNumber(0, 6.00);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double addFuelToTank(double fuel) {
        if (this.fuelLevel + fuel > fuelTankSize) {
            System.out.println("Added! 1" +
                    "");
            return 1;
        }

        this.fuelLevel += fuel;

        if (this.fuelLevel + fuel < this.fuelTankSize) {
            return -(this.fuelTankSize - (this.fuelTankSize + fuel));
        }
        System.out.println("Added! 3");
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Make: %s, Model: %s, Year: %d, Fuel Tank Size: %.2f, Fuel Economy: %.2f, Optimal Speed: %.2f, Odometer: %.2f, Trip Odometer: %.2f, Color: %s. Fuel Level: %.2f", make, model, year, fuelTankSize, fuelEconomy, optimalSpeed, odometer, tripOdometer, color, fuelLevel);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Car) {
            Car car = (Car) obj;
            return this.make.equals(car.getMake()) && this.model.equals(car.getModel()) && this.year == car.year;
        }
        return false;
    }

    public boolean driveCar() {

        double distanceToDrive = this.fuelLevel * this.fuelEconomy;
        double lowerMileageFactor = 0;
        if (distanceToDrive <= distanceToTravel) {
            if (this.checkTooFastOrTooSlow()) {
                lowerMileageFactor = this.generateRandomNumber(0, 1);
            }
            this.odometer += this.distanceToTravel + this.distanceToTravel * lowerMileageFactor;
            this.tripOdometer += this.distanceToTravel + this.distanceToTravel * lowerMileageFactor;
            this.fuelLevel -= this.distanceToTravel * this.fuelEconomy + this.distanceToTravel * lowerMileageFactor;
            return true;
        }

        this.odometer += distanceToDrive;
        this.tripOdometer += distanceToDrive;
        this.fuelLevel = 0.0;

        return false;
    }

    public double getTripOdometer() {
        return Math.round(this.tripOdometer);
    }

    public void clearTripOdometer() {
        this.tripOdometer = 0;
    }

    public double getOdometer() {
        return Math.round(this.odometer);
    }

    public double getFuelLevel() {
        return this.fuelLevel;
    }

    public double getFuelTankSize() {
        return this.fuelTankSize;
    }

    public void setUpTrip(double speed, double distance) {
        this.speedToTravel = speed;
        this.distanceToTravel = distance;
    }

    private double generateRandomNumber(double from, double to) {
        return ((Math.random() * (from - to)) + to);
    }

    private boolean checkTooFastOrTooSlow() {
        if (this.speedToTravel >= optimalSpeed && this.speedToTravel < optimalSpeed) {
            return true;
        }

        return false;
    }
}