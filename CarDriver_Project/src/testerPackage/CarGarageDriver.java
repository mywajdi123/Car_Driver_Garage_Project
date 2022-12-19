package testerPackage;

import classPackage.Garage;

import java.util.Scanner;

public class CarGarageDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfCars;
        do {
            System.out.print("Please enter the number of parking spaces in the new garage: ");
            numOfCars = input.nextInt();
            if (numOfCars < 1 || numOfCars > 10) {
                System.out.println("Enter a valid integer number between 1 and 10.");
            }
        } while (numOfCars < 1 || numOfCars > 10);

        Garage garage = new Garage(numOfCars);
        garage.displayOptions();
    }


}