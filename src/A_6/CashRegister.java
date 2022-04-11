package A_6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CashRegister {
    private final Semaphore semaphore;

    public CashRegister(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void getService() throws InterruptedException {
        String customerIndex = Thread.currentThread().getName();
        if (semaphore.tryAcquire(2000, TimeUnit.MILLISECONDS)) {
            System.out.printf("покупатель №%s встал в очередь.\n", customerIndex);
            Thread.sleep(2000);
            System.out.printf("покупатель №%s сделал покупку и ушёл\n", customerIndex);
            semaphore.release();
        } else {
            System.out.printf("покупатель №%s не дождался обслуживания и перешёл в другую очередь\n", customerIndex);
            getService();
        }
    }
}
