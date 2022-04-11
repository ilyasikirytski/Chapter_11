/*
CallCenter. В организации работает несколько операторов. Оператор может
обслуживать только одного клиента, остальные должны ждать своей очереди.
Клиент может положить трубку и перезвонить еще раз через некоторое время.
 */
package A_4;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        CallCenter callCenter = new CallCenter(2);

        for (int i = 0; i < 5; i++) {
            new Client("" + i, callCenter).start();
        }
    }
}
