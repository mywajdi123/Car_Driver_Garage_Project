import classPackage.Car;

public class Program {

    public static void main(String[] args) {

        Car car = new Car();

        System.out.println(car.toString());

        System.out.println(car.addFuelToTank(10.00));
    }

}