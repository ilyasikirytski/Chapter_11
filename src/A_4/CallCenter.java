package A_4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CallCenter {
    private final Semaphore semaphore;

    CallCenter(int countOfOperators) {
        this.semaphore = new Semaphore(countOfOperators);
    }

    public void call() {
        try {
            String clientName = Thread.currentThread().getName();
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                System.out.println(clientName + " дозвонился в колл-центр");
                Thread.sleep(2000);
                System.out.println(clientName + " получил информацию и положил трубку");
                semaphore.release();
            } else {
                System.out.println(clientName + " не дождался и положил трубку");
                Thread.sleep(2000);
                System.out.println(clientName + " перезванивает");
                call();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
