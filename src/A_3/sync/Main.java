package A_3.sync;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(5);
        Car car = new Car("0");
        Car car1 = new Car("1");
        Car car2 = new Car("2");
        Car car3 = new Car("3");
        Car car4 = new Car("4");
        Car car5 = new Car("5");
        Car car6 = new Car("6");

        car.run(parking);
        car1.run(parking);
        car2.run(parking);
        car3.run(parking);
        car4.run(parking);
        car5.run(parking);
        parking.unPark(car);
        car6.run(parking);
    }
}
