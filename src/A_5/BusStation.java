package A_5;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class BusStation extends Thread {
    private Semaphore semaphore;
    private int busNumber;
    private int busCountOnStation = 0;

    BusStation(int name, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.busNumber = name;
    }


    @Override
    public void run() {
        System.out.printf("Автобус №%s подъехал к остановке.\n", busNumber);
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.printf("Автобус №%s загружет пассажиров.\n", busNumber);
                Thread.sleep(new Random().nextInt(10000));
                semaphore.release(2);
                System.out.printf("Автобус №%s загрузил пассажиров и выехал\n", busNumber);
            } else {
                System.out.printf("Автобус №%s не дождался свободного места и уехал\n", busNumber);
                Thread.sleep(new Random().nextInt(20000));
//                System.out.println("Автобус " + busNumber + " вернулся для ожидания");
                run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
