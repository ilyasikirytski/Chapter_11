package A_3.sync;

public class Car extends Thread {
    private final ParkingFun parking;

    public Car(String carName, ParkingFun parking) {
        super(carName);
        this.parking = parking;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s - arrived\n", Thread.currentThread().getName());
            parking.park();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
