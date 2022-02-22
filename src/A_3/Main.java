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

        new Thread(new Car(parking, "1")).start();
        new Thread(new Car(parking, "2")).start();
        new Thread(new Car(parking, "3")).start();
        new Thread(new Car(parking, "4")).start();
        new Thread(new Car(parking, "5")).start();

    }
}
