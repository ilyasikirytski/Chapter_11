package A_6;

public class Client extends Thread {
    CashRegister cashRegister;

    public Client(String clientNumber, CashRegister cashRegister) {
        super(clientNumber);
        this.cashRegister = cashRegister;
    }

    @Override
    public void run() {
        try {
            cashRegister.getService();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
