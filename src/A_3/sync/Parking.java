package A_3.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Parking implements ParkingFun {
    private final int timeToWait;
    private final int timeForParking;
    /*
    найти проблему с колекцией и решить
     */
    private final AtomicIntegerArray parkingPlaces;
    private final Semaphore semaphore;

    public Parking(int capacity, int timeToWait, int timeForParking) {
        parkingPlaces = new AtomicIntegerArray(capacity);
        semaphore = new Semaphore(capacity);
        this.timeToWait = timeToWait;
        this.timeForParking = timeForParking;
    }

    @Override
    public void park() throws InterruptedException {
        String carName = Thread.currentThread().getName();
        int parkNumber = 0;
        if (semaphore.tryAcquire(timeToWait, TimeUnit.MILLISECONDS)) {
            for (int i = 0; i < parkingPlaces.length(); i++) {
                if (parkingPlaces.compareAndSet(i, 0, 1)) {
                    parkNumber = i;
                    break;
                }
            }
            System.out.printf("%s - parked %s\n", carName, parkNumber);
            Thread.sleep(timeForParking);
            parkingPlaces.set(parkNumber, 0);
            System.out.printf("%s - unParked %s\n", carName, parkNumber);
            semaphore.release();
        } else {
            System.out.printf("%s - can't park\n", carName);
        }
    }
}
