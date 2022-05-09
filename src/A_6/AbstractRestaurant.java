package A_6;

import java.util.Random;
import java.util.concurrent.Semaphore;

abstract class AbstractRestaurant {
    private final String name;
    private final CashRegister[] cashRegisters;

    public AbstractRestaurant(String name, int countOfCashRegisters, Semaphore semaphore) {
        this.name = name;
        this.cashRegisters = new CashRegister[countOfCashRegisters];
        for (int i = 0; i < cashRegisters.length; i++) {
            cashRegisters[i] = new CashRegister(semaphore, i);
        }
    }

    public void serve() throws InterruptedException {
        while (!cashRegisters[getAnotherCashRegister()].tryServe(name)) {

        }
    }

    private int getAnotherCashRegister() {
        Random random = new Random();
        return random.nextInt(cashRegisters.length);
    }
}
