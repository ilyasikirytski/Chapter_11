package A_3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private int carName;
    private Semaphore semaphore;
    private Parking parking;

    public Car(Parking parking, int name) {
        this.parking = parking;
        this.semaphore = parking.semaphore;
        this.carName = name;
    }

    @Override
    public void run() {
        System.out.printf("Автомобиль %s подъехал к парковке\n", carName);
        try {
            int parkingNumber = 0;
            if (semaphore.tryAcquire(4000, TimeUnit.MILLISECONDS)) {
                for (int i = 0; i < parking.parkingPlaces.length; i++) {
                    if (!parking.parkingPlaces[i]) {
                        parking.parkingPlaces[i] = true;
                        parkingNumber = i;
                        System.out.printf("Автомобиль %s припарковался %s\n", carName, parkingNumber);
                        break;
                    }
                }
                Thread.sleep(3000);
                semaphore.release();
                parking.parkingPlaces[parkingNumber] = false;
                System.out.printf("Автомобиль %s покинул место %s\n", carName, parkingNumber);
            } else {
                System.out.printf("Автомобиль %s не дождался места и уехал\n", carName);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + carName + '\'' +
                '}';
    }
}
