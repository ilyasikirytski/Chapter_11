package A_5;

public class Bus extends Thread {
    private final Route route;

    public Bus(String name, Route route) {
        super(name);
        this.route = route;
    }

    @Override
    public void run() {
        try {
            route.drive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
