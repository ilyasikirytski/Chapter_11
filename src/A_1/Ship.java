package A_1;

import java.util.concurrent.Semaphore;

public class Ship extends Thread {
    private final Semaphore semaphore;
    private final Port deliveryFrom;
    private final Port deliveryTo;
    private final String shipName;
    private final int containerCapacity;
    private int countOfContainers;

    public Ship(Semaphore semaphore, String shipName, int containerCapacity, Port deliveryFrom, Port deliveryTo) {
        this.semaphore = semaphore;
        this.shipName = shipName;
        this.containerCapacity = containerCapacity;
        this.deliveryFrom = deliveryFrom;
        this.deliveryTo = deliveryTo;
    }

    private boolean capacity() {
        return deliveryTo.getContainerCapacity() - deliveryTo.getCountOfContainers() > 0;
    }

    @Override
    public void run() {
        while (capacity()) {
            try {
                semaphore.acquire();
                loadShip();
                unloadShip();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

    private void loadShip() {
//        synchronized (this) {
        try {
            if (deliveryFrom.getCountOfContainers() > 0) {
                this.countOfContainers = deliveryFrom.loadCargo(containerCapacity, deliveryFrom);
                printLoad();
                Thread.sleep(1000);
                deliver();
            } else {
                printLoadError();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        }
    }


    private void deliver() {
        try {
            printDelivery();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unloadShip() {
        if (capacity()) {
            deliveryTo.unloadCargo(countOfContainers, deliveryTo);
            this.countOfContainers = 0;
            printUnload();
        } else {
            printUnloadError();
        }
    }

    private void printDelivery() {
        System.out.printf("%s, start delivery \n", shipName);
        System.out.printf("%s, end delivery \n", shipName);
        System.out.println("------------------");
    }

    private void printLoad() {
        System.out.printf("%s, load %s containers\n", shipName, containerCapacity);
        System.out.println("------------------");
    }

    private void printLoadError() {
        System.out.printf("%s, LOAD ERROR. %s is empty\n", shipName, deliveryFrom.getPortName());
        System.out.println("------------------");
    }

    private void printUnload() {
        System.out.printf("%s, UNLOAD %s containers \n", shipName, containerCapacity);
        System.out.printf("count of containers in %s: %s\n", deliveryFrom.getPortName(), deliveryFrom.getCountOfContainers());
        System.out.printf("count of containers in %s: %s\n", deliveryTo.getPortName(), deliveryTo.getCountOfContainers());
        System.out.println("------------------");
    }

    private void printUnloadError() {
        System.out.printf("%s, UNLOAD ERROR. %s is full\n", shipName, deliveryTo.getPortName());
        System.out.println("------------------");
    }
}