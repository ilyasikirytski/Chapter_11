package A_3.async;

import java.util.concurrent.Semaphore;

public class Parking {
    public final boolean[] parkingPlaces = new boolean[5];
    Semaphore semaphore = new Semaphore(parkingPlaces.length, true);
}
/*
как это долдно выглядеть:
без многопоточности
в паркинге методы park()
в main вызов идёт как
Parking.park(Car car);
Parking.unPark()
 */
