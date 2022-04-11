package A_5;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Route {
    private final Semaphore semaphore;
    private final BusStop[] busStops;

    Route(int countOfBusStops, int maxCountOfBusesOnBusStop) {
        this.semaphore = new Semaphore(countOfBusStops);
        this.busStops = new BusStop[countOfBusStops];
        for (int i = 0; i < countOfBusStops; i++) {
            this.busStops[i] = new BusStop("" + i, maxCountOfBusesOnBusStop);
        }
    }

    public void drive() throws InterruptedException {
        String busNumber = Thread.currentThread().getName();
        int busStopNumber = 0;
        if (semaphore.tryAcquire(4000, TimeUnit.MILLISECONDS)) {
            for (int i = 0; i < busStops.length; i++) {
                if (busStops[i].countOfBuses > 0) {
                    busStops[i].countOfBuses -= 1;
                    System.out.printf("%s заехал на %s - загрузка\n", busNumber, i);
                    busStopNumber = i;
                    Thread.sleep(2000);
                    busStops[busStopNumber].countOfBuses += 1;
                    System.out.printf("%s покинул %s\n", busNumber, i);
                } else {
                    System.out.printf("%s нe смог заехать на %s - все места заняты\n", busNumber, i);
                }
            }
            semaphore.release();
        }
    }
}
