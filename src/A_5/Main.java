/*
Автобусные остановки. На маршруте несколько остановок. На одной
остановке может останавливаться несколько автобусов одновременно,
но не более заданного числа.
 */
package A_5;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, true);

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                new BusStation(i, semaphore).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
