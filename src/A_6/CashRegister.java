package A_6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CashRegister {
    private final int numberOfCashRegister;
    private final Semaphore semaphore;

    public CashRegister(Semaphore semaphore, int numberOfCashRegister) {
        this.semaphore = semaphore;
        this.numberOfCashRegister = numberOfCashRegister;
    }

    public boolean tryServe(String restaurantName) throws InterruptedException {
        String customerIndex = Thread.currentThread().getName();
        if (semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
            System.out.printf("%s покупатель №%s встал в очередь %s.\n", restaurantName, customerIndex, numberOfCashRegister);
            Thread.sleep(2000);
            System.out.printf("%s покупатель №%s сделал покупку в %s и ушёл\n", restaurantName, customerIndex, numberOfCashRegister);
            semaphore.release();
            return true;
        } else {
            System.out.printf("%s покупатель №%s не дождался обслуживания %s и перешёл в другую очередь\n", restaurantName, customerIndex, numberOfCashRegister);
            return false;
        }
    }
}
