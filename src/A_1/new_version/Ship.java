package A_1.new_version;

public class Ship extends Thread {
    private final int shipContainerCapacity;
    private final PortFun port;
    private final PortFun port2;

    public Ship(String shipName, int shipContainerCapacity, PortFun port, PortFun port2) {
        super(shipName);
        this.shipContainerCapacity = shipContainerCapacity;
        this.port = port;
        this.port2 = port2;
    }

    @Override
    public void run() {
        try {
            String shipName = Thread.currentThread().getName();
            System.out.printf("s%s: arrived to p1\n", shipName);
            port.loadContainers(shipContainerCapacity);
            System.out.printf("s%s: arrived to p2\n", shipName);
            port2.unLoadContainers(shipContainerCapacity);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
