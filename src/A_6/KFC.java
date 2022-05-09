package A_6;

import java.util.concurrent.Semaphore;

public class KFC extends AbstractRestaurant {

    public KFC(int countOfCashRegisters, Semaphore semaphore) {
        super("KFC", countOfCashRegisters, semaphore);
    }
}
