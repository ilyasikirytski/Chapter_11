package A_5;

public class Route {
    private final BusStop[] busStops;

    Route(int countOfBusStops, int maxCountOfBusesOnBusStop) {
        this.busStops = new BusStop[countOfBusStops];
        for (int i = 0; i < countOfBusStops; i++) {
            this.busStops[i] = new BusStop("" + i, maxCountOfBusesOnBusStop);
        }
    }

    /*
    написать метод park() на остановке. который будет лочить остановку(2 permits)
     */
    public void drive() throws InterruptedException {
        String busNumber = Thread.currentThread().getName();
        for (BusStop busStop : busStops) {
            busStop.park(busNumber);
        }
    }
}
