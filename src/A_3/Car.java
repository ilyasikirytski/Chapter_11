package A_3;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private String carName;
    private final Parking parking;
    private Semaphore semaphore;

    public Car(Parking parking, String name) {
        this.semaphore = parking.semaphore;
        this.parking = parking;
        this.carName = name;
    }

    @Override
    public void run() {
        try {
            System.out.printf("Автомобиль №%s подъехал к парковке.\n", carName);
            semaphore.acquire();
            int parkingNumber = -1;
//            synchronized (parking.parkingPlaces) {
            for (int i = 0; i < 5; i++)
                if (!parking.parkingPlaces[i]) {      //Если место свободно
                    parking.parkingPlaces[i] = true;  //занимаем его
                    parkingNumber = i;         //Наличие свободного места, гарантирует семафор
                    System.out.printf("Автомобиль №%s припарковался на месте %d.\n", carName, i);
                    break;
                }
//            }
            Thread.sleep(1000);
            parking.parkingPlaces[parkingNumber] = false;
            semaphore.release();
            System.out.printf("Автомобиль №%s покинул парковку.\n", carName);
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
