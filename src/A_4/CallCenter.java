package A_4;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CallCenter extends Thread {
    private Semaphore semaphore;
    private String name;

    CallCenter(String name, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.println("Сотруднику колл центра дозвонился клиент " + name);
                Thread.sleep(new Random().nextInt(10000));
                semaphore.release(2);
                System.out.println("Клиент " + name + " получил информацию и положил трубку");
            } else {
                System.out.println("Клиент " + name + " не дождался и положил трубку");
                Thread.sleep(new Random().nextInt(20000));
                System.out.println("Клиент " + name + " перезванивает");
                run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
