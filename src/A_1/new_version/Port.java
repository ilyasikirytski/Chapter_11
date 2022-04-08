package A_1.new_version;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Port implements PortFun {
    private final Semaphore semaphore;
    private final AtomicIntegerArray portBerths;
    public AtomicInteger countOfContainers;

    public Port(int shipCapacity, int countOfContainers) {
        this.portBerths = new AtomicIntegerArray(5);
        this.semaphore = new Semaphore(shipCapacity);
        this.countOfContainers = new AtomicInteger(countOfContainers);
    }

    @Override
    public void loadContainers(int shipContainerCapacity) throws InterruptedException {
        String shipName = Thread.currentThread().getName();
        int berthNumber = 0;
        semaphore.acquire();
        for (int i = 0; i < portBerths.length(); i++) {
            if (portBerths.compareAndSet(i, 0, 1)) {
                berthNumber = i;
                break;
            }
        }
        this.countOfContainers.set(countOfContainers.intValue() - shipContainerCapacity);
        System.out.printf("p1 / s%s / b%s: load %s-containers/ countOfContainers-p1-%s\n",
                shipName, berthNumber, shipContainerCapacity, this.countOfContainers.get());
        portBerths.set(berthNumber, 0);
        System.out.printf("p1 / s%s / b%s: go to port2\n", shipName, berthNumber);
        semaphore.release();
    }


    @Override
    public void unLoadContainers(int shipContainerCapacity) throws InterruptedException {
        String shipName = Thread.currentThread().getName();
        semaphore.acquire();
        int berthNumber = 0;
        for (int i = 0; i < portBerths.length(); i++) {
            if (portBerths.compareAndSet(i, 0, 1)) {
                berthNumber = i;
                break;
            }
        }
        this.countOfContainers.set(countOfContainers.intValue() - shipContainerCapacity);
        System.out.printf("p2 / s%s / b%s: UnLoad %s containers/ countOfContainers-p2-%s\n",
                shipName, berthNumber, shipContainerCapacity, this.countOfContainers.get());
        portBerths.set(berthNumber, 0);
        semaphore.release();
    }
}

