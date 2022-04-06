package A_3.sync;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking(10, 4000, 4000);
        for (int i = 0; i < 20; i++) {
            new Car("" + i, parking).start();
        }
    }
}
