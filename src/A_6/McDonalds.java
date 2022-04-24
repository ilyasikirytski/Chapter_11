package A_6;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class McDonalds {
    private final CashRegister[] cashRegisters;

    public McDonalds(int countOfCashRegisters, Semaphore semaphore) {
        this.cashRegisters = new CashRegister[countOfCashRegisters];
        for (int i = 0; i < cashRegisters.length; i++) {
            cashRegisters[i] = new CashRegister(semaphore, i);
        }
    }

    public void getServ() throws InterruptedException {
        if (!cashRegisters[getAnotherCashRegister()].getService()) {
            cashRegisters[getAnotherCashRegister()].getService();
        }
    }

    private int getAnotherCashRegister(){
        Random random = new Random();
        return random.nextInt(cashRegisters.length);
    }
}
