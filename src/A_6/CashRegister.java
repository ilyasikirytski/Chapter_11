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
/*
написать вторую кассу
 */
    public boolean getService() throws InterruptedException {
        String customerIndex = Thread.currentThread().getName();
        if (semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
            System.out.printf("покупатель №%s встал в очередь %s.\n", customerIndex, numberOfCashRegister);
            Thread.sleep(2000);
            System.out.printf("покупатель №%s сделал покупку в %s и ушёл\n", customerIndex, numberOfCashRegister);
            semaphore.release();
            return true;
        } else {
            System.out.printf("покупатель №%s не дождался обслуживания %s и перешёл в другую очередь\n", customerIndex, numberOfCashRegister);
            return false;
        }
    }
}
