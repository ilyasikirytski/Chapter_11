package A_5;

import java.util.concurrent.Semaphore;

public class BusStop {
    public String busStopName;
    private final Semaphore semaphore;

    public BusStop(String busStopName, int maxCountOfBusesOnBusStop) {
        this.busStopName = busStopName;
        this.semaphore = new Semaphore(maxCountOfBusesOnBusStop);
    }

    public void park(String busNumber) throws InterruptedException {
        semaphore.acquire();
        System.out.printf("%s заехал на %s - загрузка\n", busNumber, busStopName);
        Thread.sleep(1000);
        System.out.printf("%s покинул %s\n", busNumber, busStopName);
        semaphore.release();
    }
}

