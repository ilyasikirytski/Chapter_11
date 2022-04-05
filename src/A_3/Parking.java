package A_3;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Parking {
    public final boolean[] parkingPlaces = new boolean[5];
    Semaphore semaphore = new Semaphore(parkingPlaces.length, true);
}
