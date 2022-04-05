package A_3.sync;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking();
        Car car = new Car("0");
        Car car1 = new Car("1");
        Car car2 = new Car("2");
        Car car3 = new Car("3");
        Car car4 = new Car("4");
        Car car5 = new Car("5");
        Car car6 = new Car("6");
        parking.park(car);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.park(car4);
        parking.unPark(car1);
        parking.park(car5);
        parking.park(car6);
    }
}
