package A_6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CashRegister extends Thread {
    Semaphore semaphore;
    int customerIndex;

    public CashRegister(Semaphore semaphore, int customerIndex) {
        this.semaphore = semaphore;
        this.customerIndex = customerIndex;
    }

    @Override
    public void run() {
        System.out.printf("покупатель №%s встал в очередь.\n", customerIndex);
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.printf("покупатель №%s выбирает товары\n", customerIndex);
                Thread.sleep(new Random().nextInt(10000));
                semaphore.release(2);
                System.out.printf("покупатель №%s сделал покупку и ушёл\n", customerIndex);
            } else {
                System.out.printf("покупатель №%s не дождался обслуживания и перешёл в другую очередь\n", customerIndex);
                Thread.sleep(new Random().nextInt(20000));
                run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
