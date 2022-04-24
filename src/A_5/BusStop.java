package A_5;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BusStop {
    public String busStopName;
    private final Semaphore semaphore;

    public BusStop(String busStopName, int maxCountOfBusesOnBusStop) {
        this.busStopName = busStopName;
        this.semaphore = new Semaphore(maxCountOfBusesOnBusStop);
    }

    public void park(String busNumber) throws InterruptedException {
        if (semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
            System.out.printf("%s заехал на %s - загрузка\n", busNumber, busStopName);
            Thread.sleep(4000);
            System.out.printf("%s покинул %s\n", busNumber, busStopName);
        } else {
            System.out.printf("%s не смог заехать на %s - все места заняты\n", busNumber, busStopName);
        }
        semaphore.release();
    }
}
