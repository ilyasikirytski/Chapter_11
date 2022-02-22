/*
Автостоянка. Доступно несколько машиномест. На одном месте может
находиться только один автомобиль.
Если все места заняты, то автомобиль не станет ждать больше определенного времени и уедет на другую
стоянку.
 */
package A_3;

import A_4.CallCenter;

public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking();

//        new Thread(new Car(parking, "1")).start();
//        new Thread(new Car(parking, "2")).start();
//        new Thread(new Car(parking, "3")).start();
//        new Thread(new Car(parking, "4")).start();
//        new Thread(new Car(parking, "5")).start();

        for (int i = 0; i < 5; i++){
            try {
                Thread.sleep(1000);
                new Thread(new Car(parking, "" + i)).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
