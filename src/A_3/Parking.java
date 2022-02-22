package A_3;

import java.util.concurrent.Semaphore;

public class Parking {
    public final boolean[] parkingPlaces = new boolean[5];
    Semaphore semaphore = new Semaphore(5, true);

}
