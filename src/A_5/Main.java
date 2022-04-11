/*
Автобусные остановки. На маршруте несколько остановок. На одной
остановке может останавливаться несколько автобусов одновременно,
но не более заданного числа.
 */
package A_5;

public class Main {
    public static void main(String[] args) {
        Route route = new Route(5, 2);

        for (int i = 0; i < 5; i++) {
            new Bus("" + i, route).start();
        }
    }
}
