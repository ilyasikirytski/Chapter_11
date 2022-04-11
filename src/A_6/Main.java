/*
Свободная касса. В ресторане быстрого обслуживания есть несколько
касс. Посетители стоят в очереди в конкретную кассу, но могут перейти
в другую очередь при уменьшении или исчезновении там очереди.
 */
package A_6;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        CashRegister cashRegister = new CashRegister(semaphore);
        for (int i = 0; i < 10; i++) {
            new Client("" + i, cashRegister).start();
        }
    }
}
