package A_4;

public class Client extends Thread {
    CallCenter callCenter;
    private boolean informationReceived;

    public Client(String clientName, CallCenter callCenter) {
        super(clientName);
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        callCenter.call();
    }
}
