package A_3;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private String carName;
    private Semaphore semaphore;

    public Car(Parking parking, String name) {
        this.semaphore = parking.semaphore;
        this.carName = name;
    }

    @Override
    public void run() {
        System.out.printf("Автомобиль №%s подъехал к парковке.\n", carName);
        try {
            if (semaphore.tryAcquire(1, 2000, TimeUnit.MILLISECONDS)) {
                semaphore.acquire();
                System.out.println("Автомобиль " + carName + " припарковался");
                Thread.sleep(new Random().nextInt(10000));
                semaphore.release(2);
                System.out.println("Автомобиль " + carName + " выехал");
            } else {
                System.out.println("Автомобиль " + carName + " не дождался свободного места и уехал");
                Thread.sleep(new Random().nextInt(20000));
                System.out.println("Автомоблиь " + carName + " вернулся для ожидания");
                run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + carName + '\'' +
                '}';
    }
}
