package A_1;

public class Port {
    private String portName;
    private int containerCapacity;
    private int countOfContainers;

    public Port(String portName, int containerCapacity) {
        this.portName = portName;
        this.containerCapacity = containerCapacity;
    }

    public void setCountOfContainers(int countOfContainers) {
        if (countOfContainers > containerCapacity) {
            System.out.println("Порт не вмещает столько контейнеров");
            throw new ExceptionInInitializerError("Введите кол-во контейнеров меньшее либо равное вместительности порта");
        } else {
            this.countOfContainers = countOfContainers;
        }
    }

    public String getPortName() {
        return portName;
    }

    public int getContainerCapacity() {
        return containerCapacity;
    }

    public int getCountOfContainers() {
        return countOfContainers;
    }

    public int loadCargo(int countOfCargo, Port deliveryFrom) {
        if (deliveryFrom.countOfContainers > 0) {
            deliveryFrom.countOfContainers -= countOfCargo;
        }
        return countOfCargo;
    }

    public void unloadCargo(int countOfCargo, Port deliveryTo) {
        deliveryTo.countOfContainers += countOfCargo;
    }
}
