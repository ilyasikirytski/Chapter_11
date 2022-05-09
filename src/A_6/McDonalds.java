package A_6;

import java.util.concurrent.Semaphore;

public class McDonalds extends AbstractRestaurant {

    public McDonalds(int countOfCashRegisters, Semaphore semaphore) {
        super("McDonalds", countOfCashRegisters, semaphore);
    }
}
