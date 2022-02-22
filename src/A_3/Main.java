/*
Автостоянка. Доступно несколько машиномест. На одном месте может
находиться только один автомобиль.
Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет на другую
стоянку.
 */
package A_3;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking();

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                new Thread(new Car(parking, "" + i)).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
