package A_1.new_version;

public class Main {
    public static void main(String[] args) {
        Port port = new Port(5, 50);
        Port port2 = new Port(5, 0);

        for (int i = 0; i < 5; i++) {
            new Ship("" + i, 10, port, port2).start();
        }
    }
}
